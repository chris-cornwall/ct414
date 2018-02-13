
package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import server.Student;

public class ExamEngine implements ExamServer {
	
	
	public static List<Student> students = new ArrayList<Student>();

    // Constructor is required
    public ExamEngine() {
        super();
    }

    // Implement the methods defined in the ExamServer interface...
    // Return an access token that allows access to the server for some time period
    public int login(int studentid, String password) throws 
                UnauthorizedAccess, RemoteException {
    	
    	Student s1 = new Student(1, "pass1");
		Student s2 = new Student(2, "pass2");
		Student s3 = new Student(3, "pass3");
		students.add(s1);
		students.add(s2);
		students.add(s3);
		
		Student search = new Student(studentid, password);
		
		for (Student s : students)
			if(s.getUserName()== search.getUserName() && s.getPassword().equals(search.getPassword())){
                            System.out.println("Student found!!");
                            return 0;
                            
                        }    
				
			else{
				return 1;
                                
			}
			

	// TBD: You need to implement this method!
	// For the moment method just returns an empty or null value to allow it to compile

	return 0;	
    }

	// Return a summary list of Assessments currently available for this studentid
    public List<String> getAvailableSummary(int token, int studentid) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {

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
