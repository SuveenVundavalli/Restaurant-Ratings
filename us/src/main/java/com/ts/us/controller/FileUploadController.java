package com.ts.us.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ts.us.dao.IBranchDAO;
import com.ts.us.dao.ICuisineDAO;
import com.ts.us.dao.IRecipeDAO;
import com.ts.us.dao.IRestaurantDAO;
import com.ts.us.dto.Branch;
import com.ts.us.dto.Restaurant;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.util.FileUtil;

@Controller
public class FileUploadController {

	private static final String IMAGESLOCATION = "//Users//suveen//Documents//EclipseWorkspace//Restaurant-Ratings//us//src//main//webapp//resources//images";

	@Autowired
	private IBranchDAO branchDAO;
	@Autowired
	private ICuisineDAO cuisineDAO;
	@Autowired
	private IRecipeDAO recipeDAO;
	@Autowired
	private IRestaurantDAO restaurantDAO;

	@PostMapping("/restaurant_registration_spring")
	public ModelAndView registerRestaurant(@ModelAttribute Restaurant restaurant,
			@RequestParam("registration_logo") CommonsMultipartFile file) throws UrbanspoonException {
		ModelAndView mv = new ModelAndView("redirect:home");
		System.out.println(restaurant);
		restaurant = restaurantDAO.insert(restaurant);
		if (restaurant.getId() != 0) {
			FileUtil.upload(IMAGESLOCATION + File.separator + "restaurants", file, restaurant.getId() + ".jpg");
			restaurantDAO.updateLogoAddress(restaurant.getId(), restaurant.getId() + ".jpg");
		}
		return mv;
	}
	@PostMapping("/branch_spring")
	public ModelAndView addBranches(@RequestParam("location") String location, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("country") String country,
			@RequestParam("postalCode") int postalCode, @RequestParam("branch_images") CommonsMultipartFile[] files,
			HttpServletRequest request) throws UrbanspoonException {
		HttpSession session = request.getSession(false);
		ModelAndView mv = null;
		long loggedInUserId = 0;
		if (session != null) {
			loggedInUserId = (Long) session.getAttribute("loggedInUserId");
		}
		if (loggedInUserId != 0) {
			mv = new ModelAndView("restaurantHome");
			Branch branch = new Branch();
			branch.setLocation(location);
			branch.setCity(city);
			branch.setState(state);
			branch.setCountry(country);
			branch.setPostalCode(postalCode);
			System.out.println(branch);
			branch = branchDAO.insert(loggedInUserId, branch);
			int count = 0;
			for (CommonsMultipartFile file : files) {
				count++;
				FileUtil.upload(IMAGESLOCATION + File.separator + "branches", file,
						branch.getId() + "_" + count + ".jpg");
				branchDAO.addImage(branch.getId(), branch.getId() + "_" + count + ".jpg");
			}
		} else {
			mv = new ModelAndView("redirect:home");
		}
		return mv;
	}

	@PostMapping("/recipe_to_branch_spring")
	public ModelAndView addRecipeToBranch(@RequestParam("branch_id") long branchId,
			@RequestParam("recipe_id") long recipeId, @RequestParam("price") float price,
			@RequestParam("recipe_image") CommonsMultipartFile file, HttpServletRequest request)
			throws UrbanspoonException {
		HttpSession session = request.getSession(false);
		ModelAndView mv = null;
		long loggedInUserId = 0;
		if (session != null) {
			loggedInUserId = (Long) session.getAttribute("loggedInUserId");
		}
		if (loggedInUserId != 0) {
			String imagePath = branchId + "_" + recipeId + ".jpg";
			FileUtil.upload(IMAGESLOCATION + File.separator + "recipes", file, imagePath);
			recipeDAO.addRecipeToBranch(recipeId, branchId, price, imagePath);
			mv = new ModelAndView("restaurantHome");
			mv.addObject("cuisineList", cuisineDAO.getCuisines(false));
			mv.addObject("branchList", branchDAO.getBranches(loggedInUserId, true, true));
			mv.addObject("recipeList", recipeDAO.getRecipes());
		} else {
			mv = new ModelAndView("redirect:home");
		}
		return mv;
	}
}
