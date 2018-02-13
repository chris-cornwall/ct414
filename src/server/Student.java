package server;

import java.util.ArrayList;
import java.util.List;


public class Student {

	private int userName;
	private String passWord;
	
	
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
	
}
