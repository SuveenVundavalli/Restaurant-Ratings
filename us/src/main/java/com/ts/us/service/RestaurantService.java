package com.ts.us.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.us.dao.IRestaurantDAO;
import com.ts.us.dto.Restaurant;
import com.ts.us.exception.UrbanspoonException;

@Service
public class RestaurantService implements IRestaurantDAO {

	@Autowired
	private IRestaurantDAO restaurantDAO;
	
	@Override
	public List<Restaurant> getRestaurants(boolean includeBranches) throws UrbanspoonException {
		return restaurantDAO.getRestaurants(includeBranches);
	}

	@Override
	public Restaurant getRestaurant(int restaurantId, boolean includeBranches) throws UrbanspoonException {
		return restaurantDAO.getRestaurant(restaurantId, includeBranches);
	}

	@Override
	public Restaurant insert(Restaurant restaurant) throws UrbanspoonException {
		return restaurantDAO.insert(restaurant);
	}

	@Override
	public Restaurant getRestaurant(String govtRegistrationId, boolean includeBranches) throws UrbanspoonException {
		return restaurantDAO.getRestaurant(govtRegistrationId, includeBranches);
	}

	@Override
	public boolean updateLogoAddress(long restaurantId, String fileName) throws UrbanspoonException {
		return restaurantDAO.updateLogoAddress(restaurantId, fileName);
	}

}
