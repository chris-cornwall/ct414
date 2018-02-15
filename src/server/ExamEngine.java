
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
    
    public void testSetup(){
        TestDriver driver = new TestDriver();
        students = driver.init();
//         //TODO: Change where we create student array
//        //Create Arrays of questions 
//        String[] answerOptions = {"yellow", "blue", "orange"};
//        Question testQ = (Question) new MCQ(1, "What is life?", answerOptions );
//        ArrayList<Question> Q1 = new ArrayList(); 
//        ArrayList<Question> Q2 = new ArrayList();
//        ArrayList<Question> Q3 = new ArrayList();     
//        Q1.add(testQ);
//        Q2.add(testQ);
//        Q2.add(testQ);
//        
//        //Create Assessments using these questions
//        ArrayList<String> testCodes = new ArrayList();
//        testCodes.add("CT111");
//        testCodes.add("CT222");
//        testCodes.add("CT333");
//        
//        
//        //Each assessment has the same coursecodes for now... Will need to change
//        Assessment A1 = new MCQAssessment(Q1, testCodes);
//        Assessment A2 = new MCQAssessment(Q2, testCodes);
//        Assessment A3 = new MCQAssessment(Q3, testCodes);
//        
//        //Create ArrayLists of Assessments
//        ArrayList<Assessment> AL1 = new ArrayList();
//        AL1.add(A1);
//        AL1.add(A2);
//        AL1.add(A3);
//        ArrayList<Assessment> AL2 = new ArrayList();
//        AL2.add(A2);
//        ArrayList<Assessment> AL3 = new ArrayList();
//        AL3.add(A3);
//        
//        //ArrayList of Assessment summary.... Need to use for getAvailableSummary down below
//        ArrayList<String> summary = new ArrayList();
//        summary.add("Maths");
//        summary.add("English");
//        summary.add("drinking cans");
//        
//        //Create students, assign id, password and assessments
//        Student s1 = new Student(1, "pass1");
//        s1.setAssessments(AL1);
//        s1.setSummary(summary);
//        students.add(s1);
//        Student s2 = new Student(2, "pass2");
//        s2.setAssessments(AL2);
//        students.add(s2);
//        Student s3 = new Student(3, "pass3");
//        s3.setAssessments(AL3);
//        students.add(s3);
//        
//        System.out.println("Setup Complete!");
//        
    }

    // Implement the methods defined in the ExamServer interface...
    // Return an access token that allows access to the server for some time period
    public int login(int studentid, String password) throws
            UnauthorizedAccess, RemoteException {
        testSetup();
        Student search = new Student(studentid, password);


        for (Student s : students) {

            if (s.getID() == search.getID() && s.getPassWord().equals(search.getPassWord())) {
                int token = createToken();
                s.setToken(token);
                start = System.currentTimeMillis();
                System.out.println("START = " + start);
                loggedIn.add(s);
                System.out.println("ID = " + s.getID()+ " Token = " + s.getToken());
                 // Can delete try and catch... Just testing
        try {
            System.out.println("Trying getAssesment... \n");
            getAssessment(token, studentid, "CT111");
        } catch (NoMatchingAssessment ex) {
            Logger.getLogger(ExamEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
                return token;
            } else {
                throw new UnauthorizedAccess("Incorrect User ID or Password");

            }
        }
        
       
        // TBD: You need to implement this method!
        // For the moment method just returns an empty or null value to allow it to compile
        return 0;
    }

	// Return a summary list of Assessments currently available for this studentid
    public ArrayList<String> getAvailableSummary(int token, int studentid) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
        checkToken(token);
        Student s = findStudent(token, studentid);
        return s.getSummary();

    }

    // Return an Assessment object associated with a particular course code
    // Need to implement proper error handling
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
            System.err.println("No assesment found for given course code");
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

    public Student findStudent(int token, int studentID){
         for (Student s: loggedIn){
            if (s.getID() == studentID && s.getToken() == token)
                return s; 
            else
                System.err.println("Can't find this student");     
        }
          return null;
        
    }
    
    public void checkToken(int token){
        long now = System.currentTimeMillis();
        if (now - start >= 10000) { //Timeout set to 10 secs for testing
            System.out.println("Token has expired");

            for (Student s : loggedIn) {
                if (s.getToken() == token) {
                    loggedIn.remove(s);
                    loggedOut.add(s);
                }
            }
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
