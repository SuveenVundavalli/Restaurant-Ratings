package com.ts.us.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ts.us.dao.BranchImageDAO;
import com.ts.us.model.BranchImage;

public class BranchImageDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static BranchImageDAO branchImageDAO;
	
	@Autowired
	static BranchImage branchImage;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ts.us");
		context.refresh();
		branchImageDAO = (BranchImageDAO) context.getBean("branchImageDAO");
		branchImage = (BranchImage) context.getBean("branchImage");
	}
	
	@Test
	public void saveTestTestCase() {
		branchImage.setBranchId(1);
		branchImage.setImage("1.jpg");
		boolean flag = branchImageDAO.save(branchImage);
		assertEquals("saveTestTestCase",true, flag);
	}
	

}
