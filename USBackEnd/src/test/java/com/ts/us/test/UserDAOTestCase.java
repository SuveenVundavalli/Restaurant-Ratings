package com.ts.us.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ts.us.dao.UserDAO;
import com.ts.us.model.User;

public class UserDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static UserDAO userDAO;
	
	@Autowired
	static User user;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ts.us");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
		user = (User) context.getBean("user");
	}
	
	@Test
	public void saveTestTestCase() {
		user.setName("Suveen Kumar Vundavalli");
		user.setGender("M");
		user.setEmail("suveen@gmail.com");
		user.setPassword("password");
		user.setMobileNumber("8686242020");
		user.setRole("user");
		boolean flag = userDAO.save(user);
		assertEquals("saveTestTestCase",true, flag);
	}
	

}
