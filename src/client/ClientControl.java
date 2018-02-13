package client;

import javax.swing.SwingUtilities;

public class ClientControl {
	
	private int token;

	public int getToken(){
		return this.token;
	}
	
	public void setToken(int newToken){
		this.token = newToken;
	}
	
	public static void main(String[] args) {
		   
		   if (System.getSecurityManager() == null) {
	           System.setSecurityManager(new SecurityManager());
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
