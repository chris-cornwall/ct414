package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import server.ExamServer;


public class ClientControl {
	
	public static ExamServer server;
	private static int token;

	public int getToken(){
		return ClientControl.token;
	}
	
	public static void setToken(int newToken){
		token = newToken;
	}
        
      
	
	public static void main(String[] args) {
		   
		   if (System.getSecurityManager() == null) {
	           System.setSecurityManager(new SecurityManager());
	       }
		   try {
	           String name ="ExamServer";
	           Registry registry = LocateRegistry.getRegistry(1099);
	           server = (ExamServer) registry.lookup(name);
	       } catch (Exception e) {
	           System.err.println("GetAssessment exception:");
	           e.printStackTrace();
		   //connect to server and verify use
	       }
	      // Run the GUI construction in the Event-Dispatching thread for thread-safety
	      SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            new LoginScreen(); // Let the constructor do the job
	         }
	      });
	   }
}
