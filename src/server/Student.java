package server;

import java.util.ArrayList;
import java.util.List;


public class Student {
	
	private static int userName;
	private static String password;
	
	
	Student (int user, String pass){
		
		this.userName = user;
		this.password = pass;
		
	}

    public static int getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }
	
}
