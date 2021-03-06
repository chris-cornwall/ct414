package client;

import java.awt.*;       // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;    // Using Swing components and containers
import server.Assessment;
import server.InvalidOptionNumber;
import server.InvalidQuestionNumber;
import server.NoMatchingAssessment;
import server.Question;
import server.UnauthorizedAccess;

public class assessmentSummary extends JFrame{
    private ArrayList<String> assessments = new ArrayList();
    private int token;
    private static int answerScreenRunning = 0;
    
    private Assessment assignment;
    
	//private JTextField asses1, asses2, asses3;
	private JButton asses1, asses2, asses3;
	//public List<String> questionList = new ArrayList<String>();
	public String s1 = "What is the question?";
	
	public ArrayList<String> assignmentQuestions;
	
	public assessmentSummary(int token, int studentID, ArrayList<String> assessments) {
		System.out.println("UserInfo = " + token);
                this.assessments = assessments;
                this.token = token;
		
		Container ncp = getContentPane();
	    ncp.setLayout(new GridLayout(3, 2, 5, 5));  // The content-pane sets its layout
	    
	    ncp.add(new JLabel(assessments.get(0)));
	    asses1 = new JButton("Select");
	    ncp.add(asses1);
	    
	    asses1.addActionListener(new ActionListener() {
	          
	    	 @Override
	          public void actionPerformed(ActionEvent evt) {

	        	  if(answerScreenRunning == 0){
	        	  //Connect to sever to get assignment and pass it to assignment page 
		        	  try {
						assignment = ClientControl.server.getAssessment(token, studentID, "CT111");
		        	  } catch (RemoteException | UnauthorizedAccess | NoMatchingAssessment e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
		        	  }
		        	  
		        	  
		        	  SwingUtilities.invokeLater(new Runnable() {
	      	  			@Override
	      	  	         public void run() {
		        	  		answerScreenRunning = 1;
	      	  				new radioOptionAssignment(assignment,studentID, token);
	      	  			}
	      	  		  });
	    	  	   }
	        	  else{
	        		  JOptionPane.showMessageDialog(asses1, "Answer Screen Already In Use", "Error", 1);
	        	  }
	          }
	    });
	    
	    ncp.add(new JLabel(assessments.get(1)));
	    asses2 = new JButton("Select");
	    ncp.add(asses2);
	    
	    asses2.addActionListener(new ActionListener() {
	          
	    	 @Override
	          public void actionPerformed(ActionEvent evt) {

	        	  if(answerScreenRunning == 0){
	        	  //Connect to sever to get assignment and pass it to assignment page 
		        	  try {
						assignment = ClientControl.server.getAssessment(token, studentID, "CT111");
		        	  } catch (RemoteException | UnauthorizedAccess | NoMatchingAssessment e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
		        	  }
		        	  
		        	  
		        	  SwingUtilities.invokeLater(new Runnable() {
	      	  			@Override
	      	  	         public void run() {
		        	  		answerScreenRunning = 1;
	      	  				new radioOptionAssignment(assignment,studentID, token);
	      	  			}
	      	  		  });
	    	  	   }
	        	  else{
	        		  JOptionPane.showMessageDialog(asses1, "Answer Screen Already In Use", "Error", 1);
	        	  }
	          }
	    });
	    
	    ncp.add(new JLabel(assessments.get(2)));
	    asses3 = new JButton("Select");
	    ncp.add(asses3);
	    
	    asses3.addActionListener(new ActionListener() {
	          
	    	 @Override
	          public void actionPerformed(ActionEvent evt) {

	        	  if(answerScreenRunning == 0){
	        	  //Connect to sever to get assignment and pass it to assignment page 
		        	  try {
						assignment = ClientControl.server.getAssessment(token, studentID, "CT111");
		        	  } catch (RemoteException | UnauthorizedAccess | NoMatchingAssessment e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
		        	  }
		        	  
		        	  
		        	  SwingUtilities.invokeLater(new Runnable() {
	      	  			@Override
	      	  	         public void run() {
		        	  		answerScreenRunning = 1;
	      	  				new radioOptionAssignment(assignment,studentID, token);
	      	  			}
	      	  		  });
	    	  	   }
	        	  else{
	        		  JOptionPane.showMessageDialog(asses1, "Answer Screen Already In Use", "Error", 1);
	        	  }
	          }
	    });
	    
	    
		
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
	    setTitle("Assesment: "+token); // "super" Frame sets title
	    setSize(900, 300);  // "super" Frame sets initial size
	    setLocationRelativeTo(null);
	    setVisible(true); 
	}
	
	public static void setAnswerScreenRunning(int a){
		answerScreenRunning = a;
	}
	
}
