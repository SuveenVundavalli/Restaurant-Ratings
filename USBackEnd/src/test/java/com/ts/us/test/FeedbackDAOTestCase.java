package com.ts.us.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ts.us.dao.FeedbackDAO;
import com.ts.us.model.Feedback;

public class FeedbackDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static FeedbackDAO feedbackDAO;
	
	@Autowired
	static Feedback feedback;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ts.us");
		context.refresh();
		feedbackDAO = (FeedbackDAO) context.getBean("feedbackDAO");
		feedback = (Feedback) context.getBean("feedback");
	}
	
	@Test
	public void saveTestTestCase() {
		feedback.setUserId(1);
		feedback.setBranchId(1);;
		feedback.setFeedbackTypeId(1);
		feedback.setRecipeId(1);
		feedback.setFeedbackDate(new Date(System.currentTimeMillis()));
		feedback.setVisitedDate(new Date(System.currentTimeMillis()));
		feedback.setComments("Test comment");
		feedback.setRating(3);
		boolean flag = feedbackDAO.save(feedback);
		assertEquals("saveTestTestCase",true, flag);
	}
	

}
