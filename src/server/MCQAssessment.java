/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cornwall
 */
public class MCQAssessment implements Assessment {
    ArrayList<Question> questions = new ArrayList();
    ArrayList<String> courseCodes;
    Date dueDate;
    
    
    public MCQAssessment(ArrayList<Question> questions, ArrayList<String> courseCodes, Date dueDate){
        this.questions = questions;
        this.courseCodes= courseCodes;
        this.dueDate = dueDate;
            
    }

    public void setCourseCodes(ArrayList<String> courseCodes) {
        this.courseCodes = courseCodes;
    }

    public ArrayList<String> getCourseCodes() {
        return courseCodes;
    }

    @Override
    public String getInformation() {
        //System.out.println("Here's your assessment");
        return "Assessment";
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public Date getClosingDate() {
        return dueDate;
    }

    @Override
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    @Override
    public Question getQuestion(int questionNumber) throws InvalidQuestionNumber {
       Question q = findQuestion(questionNumber);
       return q;
    }

    @Override
    public void selectAnswer(int questionNumber, int optionNumber) throws InvalidQuestionNumber, InvalidOptionNumber {
        Question q = findQuestion(questionNumber);
        q.selectAnswer(optionNumber);
    }

    @Override
    public int getSelectedAnswer(int questionNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getAssociatedID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCourseCodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Question findQuestion(int questionNumber) throws InvalidQuestionNumber{
        for (Question q : questions){
           if (q.getQuestionNumber() == questionNumber)
               return q;
       }
       
        throw new InvalidQuestionNumber();
    }
}
