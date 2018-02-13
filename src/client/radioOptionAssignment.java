package client;

import java.awt.*;       // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers
import java.util.List;

public class radioOptionAssignment extends JFrame {
	
	
	public JRadioButton radioAnswer1True, radioAnswer1False;
	public JRadioButton radioAnswer2True, radioAnswer2False;
	public JRadioButton radioAnswer3True, radioAnswer3False;
	public JButton submitAnswers;
	
	//public Assesment(int i, List<String> questionList) {
	public radioOptionAssignment(int i, String question) {
		
	Container cp = getContentPane();
    cp.setLayout(new GridLayout(5, 3, 5, 5));  // The content-pane sets its layout
    
    cp.add(new JLabel("Question"));
    cp.add(new JLabel("True"));
    cp.add(new JLabel("False"));
    
    cp.add(new JLabel(question));
    radioAnswer1True = new JRadioButton();
    radioAnswer1False = new JRadioButton();
    cp.add(radioAnswer1True);
    cp.add(radioAnswer1False);
    
    cp.add(new JLabel(question));
    radioAnswer1True = new JRadioButton();
    radioAnswer1False = new JRadioButton();
    cp.add(radioAnswer1True);
    cp.add(radioAnswer1False);
    
    cp.add(new JLabel(question));
    radioAnswer1True = new JRadioButton();
    radioAnswer1False = new JRadioButton();
    cp.add(radioAnswer1True);
    cp.add(radioAnswer1False);
    
    cp.add(new JLabel(" "));
    
    submitAnswers = new JButton("Submit");
    cp.add(submitAnswers);
    
    cp.add(new JLabel(" "));
    
    submitAnswers.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
      	  System.out.println("1");
      	  
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