package client;

import java.awt.*;       // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers

import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;

import server.Assessment;
import server.ExamEngine;
import server.InvalidOptionNumber;
import server.InvalidQuestionNumber;
import server.Question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class radioOptionAssignment extends JFrame {
	
	public int numQuestions = 7;
	
	public JRadioButton radioAnswer1, radioAnswer2, radioAnswer3, radioAnswer4;
	public ButtonGroup group;
	public JButton submitAnswers;
	public int twos = 0 ,threes =0,fours = 0;
	public Question newQuestion; 
	public Assessment assessment;
	
	
	//private ArrayList<Answer> answers = new ArrayList<Answer>();
	private ArrayList<Question> questions = new ArrayList<Question>();
	public ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
	private ArrayList<int[]> completedAnswers = new ArrayList<int[]>();
	
	//public Assesment(int i, List<String> questionList) {
	//public radioOptionAssignment(ArrayList<String> assignmentQuestions, int userInfo) {
	public radioOptionAssignment(Assessment assignment, int userInfo){
	
		
	assessment = assignment;
	
	questions = assignment.getQuestions();
	
	ArrayList<Question> x = assessment.getQuestions();
	
	String[] y = x.get(1).getAnswerOptions();
	
	System.out.println(y[1]);
	
	int[] answers = {2,2,2,2,2,2,2};
	
	for (int i = 0;i<answers.length; i++){
		
		if (answers[i] == 2){
			twos ++;
		}
		else if (answers[i]==3){
			threes ++;
		}
		else if (answers[i] == 4){
			fours ++;
		}
	}

	
	int rows = twos+(threes*2)+(fours*2)+2;
	
	Container cp = getContentPane();
    cp.setLayout(new GridLayout(rows, 3, 5, 5));  // The content-pane sets its layout
    
    
    cp.add(new JLabel("Question"));
    cp.add(new JLabel(" "));
    cp.add(new JLabel(" "));
    
    //String a = questions.get(0);
    
    //cp.add(new JLabel(newQuestion);
    for (int j = 0;j<assessment.getQuestions().size(); j++){
    	
    	if(answers[j] == 2){
	    	cp.add(new JLabel("Test"));
	    	radioAnswer1 = new JRadioButton("True");
	        radioAnswer2 = new JRadioButton("Flase");
	        cp.add(radioAnswer1);
	        cp.add(radioAnswer2);
	        buttons.add(radioAnswer1);
	        buttons.add(radioAnswer2);
	        
	        group = new ButtonGroup();
	        group.add(radioAnswer1);
	        group.add(radioAnswer2); 
    	}
    	else if (answers[j] == 3){
    		cp.add(new JLabel("Test"));
	    	radioAnswer1 = new JRadioButton("1");
	    	radioAnswer2 = new JRadioButton("2");
	    	radioAnswer3 = new JRadioButton("3");
	        cp.add(radioAnswer1);
	        cp.add(radioAnswer2);
	        cp.add(new JLabel(" "));
	        cp.add(radioAnswer3);
	        cp.add(new JLabel(" "));
	        buttons.add(radioAnswer1);
	        buttons.add(radioAnswer2);
	        buttons.add(radioAnswer3);
	        buttons.add(radioAnswer4);
	        
	        group = new ButtonGroup();
	        group.add(radioAnswer1);
	        group.add(radioAnswer2);
	        group.add(radioAnswer3);
    	}
    	else if (answers[j] == 4){
    		cp.add(new JLabel("Test"));
	    	radioAnswer1 = new JRadioButton("1");
	    	radioAnswer2 = new JRadioButton("2");
	    	radioAnswer3 = new JRadioButton("3");
	    	radioAnswer4 = new JRadioButton("4");
	        cp.add(radioAnswer1);
	        cp.add(radioAnswer2);
	        cp.add(new JLabel(" "));
	        cp.add(radioAnswer3);
	        cp.add(radioAnswer4);
	        buttons.add(radioAnswer1);
	        buttons.add(radioAnswer2);
	        buttons.add(radioAnswer3);
	        buttons.add(radioAnswer4);
	        
	        group = new ButtonGroup();
	        group.add(radioAnswer1);
	        group.add(radioAnswer2);
	        group.add(radioAnswer3);
	        group.add(radioAnswer4);
    	}
    	
    }
    
    cp.add(new JLabel(" "));
    
    submitAnswers = new JButton("Submit");
    cp.add(submitAnswers);
    
    cp.add(new JLabel(" "));
    
    submitAnswers.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        	for (int i = 0; i<buttons.size();i++){
        		
        		if (buttons.get(i).isSelected()){
        			System.out.println(buttons.get(i).getLabel());
        		}
        		
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