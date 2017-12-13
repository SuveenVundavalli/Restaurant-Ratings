package com.ts.us.dao;

import java.util.List;

import com.ts.us.dto.Restaurant;
import com.ts.us.exception.UrbanspoonException;

public interface IRestaurantDAO {
	public List<Restaurant> getRestaurants(boolean includeBranches) throws UrbanspoonException;
	public Restaurant getRestaurant(int restaurantId, boolean includeBranches) throws UrbanspoonException;
	public Restaurant insert(Restaurant restaurant) throws UrbanspoonException;
	public Restaurant getRestaurant(String govtRegistrationId, boolean includeBranches) throws UrbanspoonException;
	public boolean updateLogoAddress(long restaurantId, String fileName) throws UrbanspoonException;
	
}
