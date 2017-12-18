package com.ts.us.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ts.us.dao.ServeDAO;
import com.ts.us.model.Serve;

public class ServeDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static ServeDAO serveDAO;
	
	@Autowired
	static Serve serve;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ts.us");
		context.refresh();
		serveDAO = (ServeDAO) context.getBean("serveDAO");
		serve = (Serve) context.getBean("serve");
	}
	
	@Test
	public void saveTestTestCase() {
		serve.setBranchId(1);
		serve.setRecipeId(1);
		serve.setPrice(100);
		serve.setImage("1_1.jpg");
		
		boolean flag = serveDAO.save(serve);
		assertEquals("saveTestTestCase",true, flag);
	}
	

}
