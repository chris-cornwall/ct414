package server;

import java.util.ArrayList;
import java.util.List;


public class Student {
	
<<<<<<< HEAD
	private static int userName;
	private static String password;
=======
	private int userName;
	private String passWord;
>>>>>>> 308aaa85521bed6f65982fdfc65d20f949d77488
	
	
	Student (int user, String pass){
		
		this.userName = user;
<<<<<<< HEAD
		this.password = pass;
=======
		this.passWord = pass;
		return;
>>>>>>> 308aaa85521bed6f65982fdfc65d20f949d77488
		
	}

    public static int getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }
	
	public int getUserName(){
		return userName;
	}
	
	public String getPassWord(){
		return passWord;
	}
	
}
