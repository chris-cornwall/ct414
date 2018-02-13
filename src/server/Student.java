package server;

import java.util.ArrayList;



public class Student {

	
	private int userName,token;
	private String passWord;
	private ArrayList<Assessment> assessments = new ArrayList<Assessment>();
	
	
	
	
	Student (int user, String pass){
		
		this.userName = user;
		this.passWord = pass;
		return;
		
	}

    public int getUserName() {
        return userName;
    }
	
	public String getPassWord(){
		return passWord;
	}
	
	public int getToken(){
		return token;
	}
	
	public void setToken(int newToken){
		this.token = newToken;
	}
	
	public void setAssessments(ArrayList<Assessment> newAssessments){
		this.assessments = newAssessments;
	}
	
	public ArrayList<Assessment> getAssessments(){
		return assessments;
	}
}
