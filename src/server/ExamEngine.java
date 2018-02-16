
package server;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExamEngine implements ExamServer {
    
    
    private ArrayList<Integer> randArray = new ArrayList(); // Keep track of tokens already used ***TOKENS NEED TO EXPIRE***  
    private ArrayList<Student> loggedIn = new ArrayList(); // Keep track of who's logged in
    private ArrayList<Student> students = new ArrayList(); // Keep track of all students
    private ArrayList<Student> loggedOut = new ArrayList(); // Keep track of who's logged out
    private ArrayList<Assessment> completed = new ArrayList(); //Keep track of completed assessments
    private long start;

    // Constructor is required
    public ExamEngine() {        
        super();       
    }
    // Run testDriver to populate questions, assignments and students
    public void testSetup(){
        TestDriver driver = new TestDriver();
        students = driver.init();      
    }


    // Return an access token which authenticates the user and allows access to the server for some time period
    public int login(int studentid, String password) throws
            UnauthorizedAccess, RemoteException {
        testSetup();
        Student search = new Student(studentid, password);

        for (Student s : students) {

            if (s.getID() == search.getID() && s.getPassWord().equals(search.getPassWord())) {
                int token = createToken();
                s.setToken(token);
                start = System.currentTimeMillis(); // Keeps track of when token was created
                loggedIn.add(s); // Keeps track of students who are currently logged in
                System.out.println("ID = " + s.getID()+ " Token = " + s.getToken());
                return token;
            } else {
                throw new UnauthorizedAccess("Incorrect User ID or Password");
            }
        }
        return 0;
    }

    // Return a summary list of Assessments currently available for this studentid
    @Override
    public ArrayList<String> getAvailableSummary(int token, int studentid) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
        checkToken(token); //Check that token is still valid
        Student s = findStudent(token, studentid);
        return s.getSummary();
    }

    // Return an Assessment object associated with a particular course code
    // Need to implement proper error handling
    @Override
    public Assessment getAssessment(int token, int studentid, String courseCode) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
        checkToken(token);
        Student student = findStudent(token, studentid);
        boolean codeFound = false;
           
        for (Assessment a: student.getAssessments()){
            for (String s: a.getCourseCodes()){
                if (s.equals(courseCode)){
                    codeFound = true;
                    return a;
                }
            }
        }
        
        if (!codeFound)
            throw new NoMatchingAssessment("Cannot find any matching assessments for course code: " + courseCode);
        return null;
    }

    // Submit a completed assessment
    public void submitAssessment(int token, int studentid, Assessment completed) throws 
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
        checkToken(token);
        Student student = findStudent(token, studentid);
        student.complete(completed);
    }
    
    // Keep creating random tokens if token already exists
    public int createToken(){

        boolean isAdded = false;
        int rand = (int) (Math.random() * 100);
        
        for (int i : randArray) {
            if (rand == i) {
                isAdded = true;
            }
        }
        
        if (!isAdded) 
            randArray.add(rand);
        else 
            createToken(); 
      
        return rand;
    }
    
    public void assignToken(Student student, int token){
        student.setToken(token);
    }

    public Student findStudent(int token, int studentID) throws UnauthorizedAccess{
         for (Student s: loggedIn){
            if (s.getID() == studentID && s.getToken() == token)
                return s; 
            else
                throw new UnauthorizedAccess("Unauthorized Access: Student with ID: " + studentID + " is not logged in.");     
        }
          return null;
        
    }
    
    public void checkToken(int token) throws 
                UnauthorizedAccess{
        long now = System.currentTimeMillis();
        if (now - start >= 86400000 ) { //Timeout set to 10 secs for testing
            for (Student s : loggedIn) {
                if (s.getToken() == token) {
                    loggedIn.remove(s);
                    loggedOut.add(s);
                }
            }
            throw new UnauthorizedAccess("Session has expired. Please log in again.");
        }
    }
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "ExamServer";
            ExamServer engine = new ExamEngine();
            ExamServer stub =
                (ExamServer) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind(name, stub);
            System.out.println("ExamEngine bound");
        } catch (Exception e) {
            System.err.println("ExamEngine exception:");
            e.printStackTrace();
        }
    }
}
