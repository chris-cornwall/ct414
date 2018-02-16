package client;

import java.awt.*;       // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers
import server.MCQ;
import server.MCQAssessment;

import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;

import server.Assessment;
import server.ExamEngine;
import server.InvalidOptionNumber;
import server.InvalidQuestionNumber;
import server.Question;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
	//private ArrayList<String> completedAnswers = new ArrayList<String>();
	private ArrayList<Integer> l= new ArrayList<Integer>();
	private ArrayList<ButtonGroup> groups = new ArrayList<ButtonGroup>();
	
	//public Assesment(int i, List<String> questionList) {
	//public radioOptionAssignment(ArrayList<String> assignmentQuestions, int userInfo) {
	public radioOptionAssignment(Assessment assignment, int userInfo){
	
		
	assessment = assignment;
	
	questions = assessment.getQuestions();
	
	System.out.println("num questions: "+assessment.getQuestions().size());
	
	for(int k = 0; k<questions.size();k++){
		l.add(questions.get(k).getAnswerOptions().length);
	}	
	
	for (int i = 0;i<l.size(); i++){
		
		if (l.get(i) == 2){
			twos ++;
		}
		else if (l.get(i)==3){			//this is used to determine how many lines need to be dynamically created on the screen
			threes ++;
		}
		else if (l.get(i) == 4){
			fours ++;
		}
	}
	
	
	
	
	int rows = twos+(threes*2)+(fours*2)+2;  //for 3 and 4 answer questions there need to be 2 lines to fit the answers.
	
	Container cp = getContentPane();
    cp.setLayout(new GridLayout(rows, 3, 5, 5));  // The content-pane sets its layout
    
    
    cp.add(new JLabel("Question"));
    cp.add(new JLabel(" "));			//formating the screen
    cp.add(new JLabel(" "));
    
    //String a = questions.get(0);
    
    //cp.add(new JLabel(newQuestion);
    for (int j = 0;j<assessment.getQuestions().size(); j++){
    	
    	String[]options = questions.get(j).getAnswerOptions();
    	
    	//for (int k= 0; k < questions.get(j).getAnswerOptions().length;k++){
	    	if(l.get(j)== 2){
		    	cp.add(new JLabel(questions.get(j).getQuestionDetail()));
		    	radioAnswer1 = new JRadioButton(options[0]);
		    	radioAnswer1.setActionCommand("0");
		        radioAnswer2 = new JRadioButton(options[1]);				//Loop for 2 answer questions
		        radioAnswer2.setActionCommand("1");
		        cp.add(radioAnswer1);
		        cp.add(radioAnswer2);
		        buttons.add(radioAnswer1);
		        buttons.add(radioAnswer2);
		        
		        group = new ButtonGroup();
		        group.add(radioAnswer1);
		        group.add(radioAnswer2); 
		        groups.add(group);
	    	}
	    	else if (l.get(j) == 3){
	    		cp.add(new JLabel(questions.get(j).getQuestionDetail()));
		    	radioAnswer1 = new JRadioButton(options[0]);
		    	radioAnswer1.setActionCommand("0");
		    	radioAnswer2 = new JRadioButton(options[1]);
		    	radioAnswer2.setActionCommand("1");
		    	radioAnswer3 = new JRadioButton(options[2]);
		    	radioAnswer3.setActionCommand("2");
		        cp.add(radioAnswer1);
		        cp.add(radioAnswer2);							//loops for 3 answer questions
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
		        groups.add(group);
	    	}
	    	else if (l.get(j) == 4){
	    		cp.add(new JLabel(questions.get(j).getQuestionDetail()));
		    	radioAnswer1 = new JRadioButton(options[0]);
		    	radioAnswer1.setActionCommand("0");
		    	radioAnswer2 = new JRadioButton(options[1]);
		    	radioAnswer2.setActionCommand("1");
		    	radioAnswer3 = new JRadioButton(options[2]);
		    	radioAnswer3.setActionCommand("2");
		    	radioAnswer4 = new JRadioButton(options[3]);
		    	radioAnswer4.setActionCommand("3");				//loop for 4 answer questions
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
		        groups.add(group);
	    	}
    	//}
    	
    }
    
    cp.add(new JLabel(" "));
    
    submitAnswers = new JButton("Submit");
    cp.add(submitAnswers);
    
    cp.add(new JLabel(" "));
    
    submitAnswers.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        	String x = null;
        	
        	for (int i = 0; i<groups.size();i++){
      		
        			x = groups.get(i).getSelection().getActionCommand(); // reads in the ActionCommand for the button selected in int form for each question.
        		
        			System.out.println(i+"    "+x);
        			
//I couldnt figure this out, sorry!!!        			//MCQAssessment.selectAnswer(i, Integer.parseInt(x)); //
        			
        			
        			
        			
        		//}
        		
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