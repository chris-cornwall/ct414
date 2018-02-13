package client;

import java.awt.*;       // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers
import java.util.List;

public class writtenAssesment extends JFrame {
	
	public JTextField answer1,answer2,answer3;
	public JButton submitAnswers;
	
	//public Assesment(int i, List<String> questionList) {
	public writtenAssesment(int i, String question) {
		
	Container cp = getContentPane();
    cp.setLayout(new GridLayout(5, 2, 5, 5));  // The content-pane sets its layout
    
    cp.add(new JLabel("Question"));
    cp.add(new JLabel("Answer"));
    
    cp.add(new JLabel(question));
    answer1 = new JTextField(10);
    cp.add(answer1);
    
    cp.add(new JLabel(question));
    answer2 = new JTextField(10);
    cp.add(answer2);
    
    cp.add(new JLabel(question));
    answer3 = new JTextField(10);
    cp.add(answer3);
    
    cp.add(new JLabel(" "));
    
    submitAnswers = new JButton("Submit");
    cp.add(submitAnswers);
    
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