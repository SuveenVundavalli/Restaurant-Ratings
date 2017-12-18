package com.ts.us.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ts.us.dao.FeedbackTypeDAO;
import com.ts.us.model.FeedbackType;

public class FeedbackTypeDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static FeedbackTypeDAO feedbackTypeDAO;
	
	@Autowired
	static FeedbackType feedbackType;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ts.us");
		context.refresh();
		feedbackTypeDAO = (FeedbackTypeDAO) context.getBean("feedbackTypeDAO");
		feedbackType = (FeedbackType) context.getBean("feedbackType");
	}
	
	@Test
	public void saveTestTestCase() {
		feedbackType.setDescription("Overall");
		boolean flag = feedbackTypeDAO.save(feedbackType);
		assertEquals("saveTestTestCase",true, flag);
	}
	

}
