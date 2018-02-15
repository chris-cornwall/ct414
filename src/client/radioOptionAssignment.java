package client;

import java.awt.*;       // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers

import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;

import server.Assessment;
import server.ExamEngine;
import java.util.ArrayList;
import java.util.List;

public class radioOptionAssignment extends JFrame {
	
	
	public JRadioButton radioAnswer1True, radioAnswer1False;
	public JRadioButton radioAnswer2True, radioAnswer2False;
	public JRadioButton radioAnswer3True, radioAnswer3False;
	public JButton submitAnswers;
	
	private ArrayList<Answer> answers = new ArrayList<Answer>();
	
	//public Assesment(int i, List<String> questionList) {
	//public radioOptionAssignment(ArrayList<String> assignmentQuestions, int userInfo) {
	public radioOptionAssignment(Assessment assignment, int userInfo) {
			
	Container cp = getContentPane();
    cp.setLayout(new GridLayout(5, 3, 5, 5));  // The content-pane sets its layout
    
    
    cp.add(new JLabel("Question"));
    cp.add(new JLabel("True"));
    cp.add(new JLabel("False"));
    
    cp.add(new JLabel("Test"));
    radioAnswer1True = new JRadioButton();
    radioAnswer1False = new JRadioButton();
    cp.add(radioAnswer1True);
    cp.add(radioAnswer1False);
    
    ButtonGroup g1 = new ButtonGroup();
    g1.add(radioAnswer1False);
    g1.add(radioAnswer1True); 
    
    cp.add(new JLabel("Test"));
    radioAnswer2True = new JRadioButton();
    radioAnswer2False = new JRadioButton();
    cp.add(radioAnswer2True);
    cp.add(radioAnswer2False);
    
    cp.add(new JLabel("Test"));
    radioAnswer3True = new JRadioButton();
    radioAnswer3False = new JRadioButton();
    cp.add(radioAnswer3True);
    cp.add(radioAnswer3False);
    
    cp.add(new JLabel(" "));
    
    ButtonGroup g2 = new ButtonGroup();
    g2.add(radioAnswer2False);
    g2.add(radioAnswer2True);
    
    ButtonGroup g3 = new ButtonGroup();
    g3.add(radioAnswer3False);
    g3.add(radioAnswer3True);
    
    submitAnswers = new JButton("Submit");
    cp.add(submitAnswers);
    
    cp.add(new JLabel(" "));
    
    submitAnswers.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        
        	if(radioAnswer1False.isSelected() == true){
        		Answer A1 = new Answer(0);
        		
        		answers.add(A1);
        	}
        	else if(radioAnswer1True.isSelected() == true){
        		Answer A1 = new Answer(1);
        		
        		answers.add(A1);
        	}
        	
        	if(radioAnswer2False.isSelected() == true){
        		Answer A2 = new Answer(0);
        		
        		answers.add(A2);
        	}
        	else if(radioAnswer2True.isSelected() == true){
        		Answer A2 = new Answer(1);
        		
        		answers.add(A2);
        	}
        	
        	if(radioAnswer3False.isSelected() == true){
        		Answer A3 = new Answer(0);
        		
        		answers.add(A3);
        	}
        	else if(radioAnswer3True.isSelected() == true){
        		Answer A3 = new Answer(1);
        		
        		answers.add(A3);
        	}
      	  
        }
    });
	
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Exit program if close-window button clicked
    setTitle("Assesment for "+ userInfo +":"); // "super" Frame sets title
    setSize(900, 300);  // "super" Frame sets initial size
    setLocationRelativeTo(null);
    setVisible(true);
	}
	
}