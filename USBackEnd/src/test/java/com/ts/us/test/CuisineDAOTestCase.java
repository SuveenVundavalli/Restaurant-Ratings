package com.ts.us.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ts.us.dao.CuisineDAO;
import com.ts.us.model.Cuisine;

public class CuisineDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static CuisineDAO cuisineDAO;
	
	@Autowired
	static Cuisine cuisine;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ts.us");
		context.refresh();
		cuisineDAO = (CuisineDAO) context.getBean("cuisineDAO");
		cuisine = (Cuisine) context.getBean("cuisine");
	}
	
	@Test
	public void saveTestTestCase() {
		cuisine.setCountry("India");
		cuisine.setName("South India");
		boolean flag = cuisineDAO.save(cuisine);
		assertEquals("saveTestTestCase",true, flag);
	}
	

}
