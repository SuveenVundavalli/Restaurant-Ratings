package com.ts.us.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ts.us.dao.RestaurantDAO;
import com.ts.us.dto.Restaurant;

@Controller
public class UrbanspoonController {
		
	@RequestMapping("/home")
	public ModelAndView goToHomePage() {
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView("home");
			RestaurantDAO restaurantDAO = new RestaurantDAO();
			List<Restaurant> restaurantsList = restaurantDAO.getRestaurants(true);
			modelAndView.addObject("restaurantsList", restaurantsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
}
