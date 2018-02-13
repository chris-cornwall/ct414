package client;

import java.awt.*;       // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.*;    // Using Swing components and containers
import server.ExamServer;
 
// A Swing GUI application inherits the top-level container javax.swing.JFrame
public class LoginScreen extends JFrame {
   private JTextField user, pass;
   private JButton submit ;
   private boolean isVerified = false;
   
   // Constructor to setup the GUI components and event handlers
   public LoginScreen() {
      // Retrieve the content-pane of the top-level container JFrame
      // All operations done on the content-pane
      Container cp = getContentPane();
      cp.setLayout(new GridLayout(3, 2, 5, 5));  // The content-pane sets its layout
 
      cp.add(new JLabel("Username: "));
      user = new JTextField(10);
      cp.add(user);
      cp.add(new JLabel("Password: "));
      pass = new JTextField(10);
      cp.add(pass);
      
      cp.add(new JLabel(" "));
      
      submit = new JButton("Submit");
      cp.add(submit);
      
      submit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
        	  	String userInfoString = user.getText();
        	  	String passInfo = pass.getText();
        	  	int userInfo = Integer.parseInt(userInfoString);
        	  	
  
        	  	verifyUser(userInfo, passInfo);
        	  	if(isVerified == true){
        	  		SwingUtilities.invokeLater(new Runnable() {
        	  			@Override
        	  	         public void run() {
        	  				new assesmentSummary(userInfoString);
        	  			 }
        	  		});
	        	  		cp.setVisible(false);
	        	  		dispose();
	        	  		isVerified = false;
        	  		
        	  	}
        	  
          }
       });
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
      setTitle("Login"); // "super" Frame sets title
      setSize(900, 300);  // "super" Frame sets initial size
      setLocationRelativeTo(null);
      setVisible(true);   // "super" Frame shows
   }
   
   public void verifyUser(int user, String pass){
	   
	   
       try {
           String name ="ExamServer";
           Registry registry = LocateRegistry.getRegistry(1099);
           ExamServer server = (ExamServer) registry.lookup(name);
           int test = server.login(user, pass);
          // System.out.println(test);
           if (test == 0)
        	   isVerified = true;
       } catch (Exception e) {
           System.err.println("GetAssessment exception:");
           e.printStackTrace();
	   //connect to server and verify use
       }
       
   }
 
   // The entry main() method
   
}