/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author cornwall
 */
public class MCQ implements Question{
    private ArrayList<String> answers = new ArrayList();
    private String[] answerOptions;
    private String questionDetail;
    private Date dueDate;
    private int id;
    
    
    public MCQ(int id, String details, String[] answerOptions){
        this.id = id;
        this.questionDetail = details;
        this.answerOptions = answerOptions;  
    }
    
    public boolean isAvailable(Date now){
        if(now.compareTo(dueDate) >= 0)        
            return false;
        else
            return true;
    }



    @Override
    public int getQuestionNumber() {
        return id;
    }

    @Override
    public String getQuestionDetail() {
        return questionDetail;
    }
    
    @Override
    public String[] getAnswerOptions() {
        return answerOptions;
    }

    @Override
    public void answerQuestions(ArrayList<String> answers) {
        this.answers = answers;
    }
    
}
