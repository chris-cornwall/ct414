package client;

import java.util.ArrayList;

public class Answer {
	
	//private int questionNumber;
	private int answer;

	public Answer(int newAnswer){
		
		this.answer = newAnswer;
		
	}
/*
	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	*/
	

	public int getAnswers() {
		return answer;
	}

	public void setAnswers (int answer) {
		this.answer = answer;
	}
	
	 
}
