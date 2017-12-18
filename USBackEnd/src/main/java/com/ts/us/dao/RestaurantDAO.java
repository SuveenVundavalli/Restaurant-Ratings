package com.ts.us.dao;

import java.util.List;

import com.ts.us.model.Restaurant;

public interface RestaurantDAO {
	boolean save(Restaurant restaurant);	
	boolean update(Restaurant restaurant);	
	boolean delete(int id);	
	Restaurant get(int id);
	Restaurant get(String govtRegistrationId);
	List<Restaurant> list();
}
