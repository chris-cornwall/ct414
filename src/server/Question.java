// Question.java

package server;

import java.io.Serializable;
import java.util.ArrayList;

public interface Question extends Serializable {

	// Return the question number
	public int getQuestionNumber();

	// Return the question text
	public String getQuestionDetail();

	// Return the possible answers to select from
	public String[] getAnswerOptions();
        
        // Answer a set of questions
        public void answerQuestions(ArrayList<String> answers);
        
        
       

}
