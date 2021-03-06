// Assessment.java 
package server;

import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public interface Assessment extends Serializable {

    // Return information about the assessment	
    public String getInformation();

    // Return the final date / time for submission of completed assessment
    public Date getClosingDate();

    // Return a list of all questions and anser options
    public ArrayList<Question> getQuestions();

    // Return one question only with answer options
    public Question getQuestion(int questionNumber) throws
            InvalidQuestionNumber;

    // Answer a particular question
    public void selectAnswer(int questionNumber, int optionNumber) throws
            InvalidQuestionNumber, InvalidOptionNumber;

    // Return selected answer or zero if none selected yet
    public int getSelectedAnswer(int questionNumber);

    // Return studentid associated with this assessment object
    // This will be preset on the server before object is downloaded
    public int getAssociatedID();

    public ArrayList<String> getCourseCodes();

    public void setCourseCodes();

}
