package com.ts.us.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ts.us.dao.RestaurantDAO;
import com.ts.us.model.Restaurant;

public class RestaurantDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static RestaurantDAO restaurantDAO;
	
	@Autowired
	static Restaurant restaurant;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ts.us");
		context.refresh();
		restaurantDAO = (RestaurantDAO) context.getBean("restaurantDAO");
		restaurant = (Restaurant) context.getBean("restaurant");
	}
	
	//@Test
	public void saveTestTestCase() {
		restaurant.setGovtRegistrationId("TS123");
		restaurant.setName("Gopal Food Court");
		restaurant.setPassword("password");
		restaurant.setLogo("1.jpg");
		
		boolean flag = restaurantDAO.save(restaurant);
		assertEquals("saveTestTestCase",true, flag);
	}
	
	@Test
	public void listRestaurantsTestCase() {
		List<Restaurant> restaurants = restaurantDAO.list();
		System.out.println(restaurants);
		assertEquals(1, restaurants.size());
	}

}
