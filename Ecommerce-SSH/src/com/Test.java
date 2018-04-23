package com;
import com.dao.impl.UserDAO;


public class Test {
	public static void main(String[] args)
	{
		UserDAO u = new UserDAO();
		if(u.checkName("123"))
			System.out.println("true");
		else
			System.out.println("false");
	}
}
