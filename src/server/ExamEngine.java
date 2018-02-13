
package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import server.Student;

public class ExamEngine implements ExamServer {
    
    
    private ArrayList<Integer> randArray = new ArrayList(); // Keep track of tokens already used ***TOKENS NEED TO EXPIRE***  
    private ArrayList<Student> loggedIn = new ArrayList(); // Keep track of who's logged in
    private ArrayList<Student> students = new ArrayList(); // Keep track of all students

    // Constructor is required
    public ExamEngine() {        
        super();
        
    }
    
    public void testSetup(){
         //TODO: Change where we create student array
        //Create Arrays of questions 
        Question testQ = (Question) new AssignmentQuestion();
        ArrayList<Question> Q1 = new ArrayList(); 
        ArrayList<Question> Q2 = new ArrayList();
        ArrayList<Question> Q3 = new ArrayList();     
        Q1.add(testQ);
        Q2.add(testQ);
        Q2.add(testQ);
        
        //Create Assessments using these questions
        Assessment A1 = new Assessment1(Q1);
        Assessment A2 = new Assessment1(Q2);
        Assessment A3 = new Assessment1(Q3);
        
        //Create ArrayLists of Assessments
        ArrayList<Assessment> AL1 = new ArrayList();
        AL1.add(A1);
        AL1.add(A2);
        AL1.add(A3);
        ArrayList<Assessment> AL2 = new ArrayList();
        AL2.add(A2);
        ArrayList<Assessment> AL3 = new ArrayList();
        AL3.add(A3);
        
        //ArrayList of Assessment summary.... Need to use for getAvailableSummary down below
        ArrayList<String> summary = new ArrayList();
        summary.add("Maths");
        summary.add("English");
        summary.add("drinking cans");
        
        //Create students, assign id, password and assessments
        Student s1 = new Student(1, "pass1");
        s1.setAssessments(AL1);
        s1.setSummary(summary);
        students.add(s1);
        Student s2 = new Student(2, "pass2");
        s2.setAssessments(AL2);
        students.add(s2);
        Student s3 = new Student(3, "pass3");
        s3.setAssessments(AL3);
        students.add(s3);
        
        System.out.println("Setup Complete!");
        
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
                loggedIn.add(s);
                System.out.println("ID = " + s.getID()+ " Token = " + s.getToken());
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
        for(Student s: loggedIn){
            if(s.getToken() == token && s.getID() == studentid)
                System.out.println("LOOK BELOW");
                for (int i=0; i<s.getSummary().size(); i++)
                System.out.println(s.getSummary().get(i));
               return s.getSummary();           
        }

        // TBD: You need to implement this method!
        // For the moment method just returns an empty or null value to allow it to compile

        return null;
    }

    // Return an Assessment object associated with a particular course code
    public Assessment getAssessment(int token, int studentid, String courseCode) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

        // TBD: You need to implement this method!
        // For the moment method just returns an empty or null value to allow it to compile

        return null;
    }

    // Submit a completed assessment
    public void submitAssessment(int token, int studentid, Assessment completed) throws 
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

        // TBD: You need to implement this method!
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
