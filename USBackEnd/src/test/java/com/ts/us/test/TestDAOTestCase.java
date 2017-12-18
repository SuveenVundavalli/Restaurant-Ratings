package com.ts.us.test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ts.us.dao.TestDAO;
import com.ts.us.model.Test01;

public class TestDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static TestDAO testDAO;
	
	@Autowired
	static Test01 test01;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ts.us");
		context.refresh();
		testDAO = (TestDAO) context.getBean("testDAO");
		test01 = (Test01) context.getBean("test01");
	}
	
	@Test
	public void saveTestTestCase() {
		//test01.setId(1);
		test01.setName("Suveen");
		boolean flag = testDAO.save(test01);
		assertEquals("saveTestTestCase",true, flag);
	}
	

}
