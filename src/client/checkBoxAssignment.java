package client;

import java.awt.*;       // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers
import java.util.List;

public class checkBoxAssignment extends JFrame {
	
	public JCheckBox checkAnswer1True,checkAnswer1False;
	public JCheckBox checkAnswer2True,checkAnswer2False;
	public JCheckBox checkAnswer3True,checkAnswer3False;
	public JButton submitAnswers;
	
	//public Assesment(int i, List<String> questionList) {
	public checkBoxAssignment(int i, String question) {
		
	Container cp = getContentPane();
    cp.setLayout(new GridLayout(5, 2, 5, 5));  // The content-pane sets its layout
    
    cp.add(new JLabel("Question"));
    cp.add(new JLabel("True"));
    cp.add(new JLabel("False"));
 
    cp.add(new JLabel(question));
    checkAnswer1True = new JCheckBox();
    checkAnswer1False = new JCheckBox();
    cp.add(checkAnswer1True);
    cp.add(checkAnswer1False);
    
    cp.add(new JLabel(question));
    checkAnswer2True = new JCheckBox();
    checkAnswer2False = new JCheckBox();
    cp.add(checkAnswer2True);
    cp.add(checkAnswer2False);
    
    cp.add(new JLabel(question));
    checkAnswer3True = new JCheckBox();
    checkAnswer3False = new JCheckBox();
    cp.add(checkAnswer3True);
    cp.add(checkAnswer3False);
    
    cp.add(new JLabel(" "));
    
    submitAnswers = new JButton("Submit");
    cp.add(submitAnswers);
    
    cp.add(new JLabel(" "));
    
    submitAnswers.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
      	  System.out.println(" yup ");
      	  
      	//Connect to sever and check the answers
      	  
        }
    });
	
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Exit program if close-window button clicked
    setTitle("Assesment "+ i +":"); // "super" Frame sets title
    setSize(900, 300);  // "super" Frame sets initial size
    setLocationRelativeTo(null);
    setVisible(true);
	}
	
}