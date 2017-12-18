package com.ts.us.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ts.us.model.Restaurant;
import com.ts.us.service.UrbanspoonService;

@Controller
public class UrbanspoonController {
	@Autowired
	private UrbanspoonService urbanspoonService;
	
	@RequestMapping("/home")
	public ModelAndView goToHome() {
		ModelAndView mv = new ModelAndView("home");
		List<Restaurant> restaurants = urbanspoonService.getAllRestaurants(true, true, true, true);
		mv.addObject("restaurantsList", restaurants);
		mv.addObject("isUserAtHomePage", true);
		return mv;
	}
}
