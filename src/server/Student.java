package server;

import java.util.ArrayList;

public class Student {

    private int userID, token;
    private String password;
    private ArrayList<Assessment> assessments = new ArrayList<Assessment>();
    private ArrayList<String> assessSummary = new ArrayList<String>();
    private ArrayList<Assessment> completed = new ArrayList<Assessment>();

    Student(int user, String pass) {

        this.userID = user;
        this.password = pass;
        return;

    }

    public int getID() {
        return userID;
    }

    public String getPassWord() {
        return password;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int newToken) {
        this.token = newToken;
    }

    public void setAssessments(ArrayList<Assessment> newAssessments) {
        this.assessments = newAssessments;
    }

    public ArrayList<Assessment> getAssessments() {
        return assessments;
    }

    public ArrayList<String> getSummary() {
        return assessSummary;
    }

    public void setSummary(ArrayList<String> assessSummary) {
        this.assessSummary = assessSummary;
    }
    
    public void complete(Assessment assessment){
        completed.add(assessment);
    }
    
    public ArrayList<Assessment> getCompleted(){
        return completed;
    }

}
