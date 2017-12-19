package com.ts.us.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ts.us.model.Branch;
import com.ts.us.model.BranchImage;
import com.ts.us.model.Cuisine;
import com.ts.us.model.Recipe;
import com.ts.us.model.Restaurant;
import com.ts.us.model.Serve;
import com.ts.us.model.User;
import com.ts.us.service.UrbanspoonService;
import com.ts.us.util.FileUtil;

@Controller
public class UrbanspoonController {
	@Autowired
	private UrbanspoonService urbanspoonService;

	@Autowired
	private HttpSession session;

	private static final String IMAGESLOCATION = "/Users/suveen/Documents/EclipseWorkspace/Restaurant-Ratings/USFrontEnd/src/main/webapp/resources/images/";

	@RequestMapping("/home")
	public ModelAndView goToHome() {
		ModelAndView mv = new ModelAndView("home");
		List<Restaurant> restaurants = getAllRestaurants();
		mv.addObject("restaurantsList", restaurants);
		mv.addObject("isUserAtHomePage", true);
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam("userId") String userId, @RequestParam("password") String password,
			@RequestParam("loginAs") String loginAs) {
		ModelAndView mv = null;
		if (loginAs != null && loginAs.equals("user")) {
			User user = urbanspoonService.getUser(userId);
			if (user != null && user.getPassword().equals(password)) {
				session.setAttribute("loggedInUser", user);
				session.setAttribute("loggedInUserId", user.getId());
				session.setAttribute("loggedInAs", "user");
				mv = new ModelAndView("home");
				mv.addObject("restaurantsList", getAllRestaurants());
				mv.addObject("successMessage", "Welcome "+user.getName()+".");
			}
		} else if (loginAs != null && loginAs.equals("restaurant")) {
			Restaurant restaurant = urbanspoonService.getRestaurant(userId, false);
			System.out.println(restaurant);
			if (restaurant != null && restaurant.getPassword().equals(password)) {
				session.setAttribute("loggedInUser", restaurant);
				session.setAttribute("loggedInUserId", restaurant.getId());
				session.setAttribute("loggedInAs", "restaurant");
				mv = new ModelAndView("home");
				mv.addObject("successMessage", "Welcome "+restaurant.getName()+".");
				mv.addObject("restaurant", restaurant);
			}
		} else {
			mv = new ModelAndView("home");
			List<Restaurant> restaurantsList = getAllRestaurants();
			mv.addObject("restaurantsList", restaurantsList);
			mv.addObject("errorMessage", "Login Failed....");
		}

		return mv;
	}

	@RequestMapping("/signOut")
	public ModelAndView signOut() {
		session.invalidate();
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserAtHomePage", true);
		mv.addObject("restaurantsList", getAllRestaurants());
		return mv;
	}

	@PostMapping("/userRegistration")
	public ModelAndView registerUser(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("gender") String gender,
			@RequestParam("mobileNumber") String mobileNumber) {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("restaurantsList", getAllRestaurants());
		User user = new User();
		user.setName(firstName + " " + lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setMobileNumber(mobileNumber);
		user.setGender(gender);
		user.setRole("user");
		if (urbanspoonService.register(user)) {
			session.setAttribute("loggedInUser", user);
			session.setAttribute("loggedInUserId", user.getId());
			session.setAttribute("loggedInAs", "user");
		}
		return mv;
	}

	@PostMapping("/restaurantRegistration")
	public ModelAndView registerRestaurant(@ModelAttribute Restaurant restaurant,
			@RequestParam("registrationLogo") CommonsMultipartFile file) {
		ModelAndView mv = new ModelAndView("redirect:home");
		System.out.println(restaurant);
		if (urbanspoonService.register(restaurant)) {
			restaurant = urbanspoonService.getRestaurant(restaurant.getGovtRegistrationId());
		}
		if (restaurant.getId() != 0) {
			FileUtil.upload(IMAGESLOCATION + "restaurants", file, restaurant.getId() + ".jpg");
			restaurant.setLogo(restaurant.getId() + ".jpg");
			urbanspoonService.updateRestaurant(restaurant);
			session.setAttribute("loggedInUser", restaurant);
			session.setAttribute("loggedInUserId", restaurant.getId());
			session.setAttribute("loggedInAs", "restaurant");
			mv.addObject("successMessage", "Signup Successfull....");
			mv.addObject("restaurant", restaurant);
		} else {
			mv.addObject("errorMessage", "Signup Failed....");
		}
		return mv;
	}
	
	@RequestMapping("/addBranch")
	public ModelAndView addBranch() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedAddBranch", true);
		return mv;
	}
	
	@PostMapping("/addBranch")
	public ModelAndView addBranches(@RequestParam("location") String location, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("country") String country,
			@RequestParam("postalCode") int postalCode, @RequestParam("branchImages") MultipartFile[] files) {
		ModelAndView mv = null;
		int loggedInUserId = 0;
		if (session != null) {
			loggedInUserId = (int) session.getAttribute("loggedInUserId");
		}
		if (loggedInUserId != 0) {
			mv = new ModelAndView("home");
			Branch branch = new Branch();
			branch.setLocation(location);
			branch.setCity(city);
			branch.setState(state);
			branch.setCountry(country);
			branch.setPostalCode(postalCode);
			branch.setRestaurantId(loggedInUserId);
			System.out.println(branch);
			if(urbanspoonService.addBranch(branch)) {
				branch = urbanspoonService.getBranch(branch.getLocation(), branch.getRestaurantId());
				int count = 0;
				for (MultipartFile file : files) {
					count++;
					FileUtil.upload(IMAGESLOCATION + "branches", file,
							branch.getId() + "_" + count + ".jpg");
					BranchImage branchImage = new BranchImage();
					branchImage.setBranchId(branch.getId());
					branchImage.setImage(branch.getId() + "_" + count + ".jpg");
					urbanspoonService.addBranchImage(branchImage);
				}
			}
			mv.addObject("successMessage","Branch successfully added...");
		} else {
			mv = new ModelAndView("redirect:home");
			mv.addObject("errorMessage", "Branch adding failed...");
		}
		return mv;
	}
	
	@RequestMapping("/addRecipe")
	public ModelAndView addRecipe() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedAddRecipe", true);
		mv.addObject("cuisinesList", urbanspoonService.getCuisine(false));
		return mv;
	}
	
	@PostMapping("/addRecipe")
	public ModelAndView addRecipe(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("isVeg") boolean isVeg, @RequestParam("cuisineId") int cuisineId) {
		ModelAndView mv = new ModelAndView("home");
		Recipe recipe = new Recipe();
		recipe.setName(name);
		recipe.setDescription(description);
		recipe.setIsVeg(isVeg);
		recipe.setCuisineId(cuisineId);
		if(urbanspoonService.addRecipe(recipe)) {
			mv.addObject("successMessage", "Recipe successfully added...");
		} else {
			mv.addObject("errorMessage", "Recipe adding failed...");
		}
		return mv;	
	}
	
	@RequestMapping("/addCuisine")
	public ModelAndView addCuisine() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedAddCuisine", true);
		return mv;
	}
	
	@PostMapping("/addCuisine")
	public ModelAndView addCuisine(@RequestParam("name") String name, @RequestParam("country") String country) {
		ModelAndView mv = new ModelAndView("home");
		Cuisine cuisine = new Cuisine();
		cuisine.setName(name);
		cuisine.setCountry(country);
		if(urbanspoonService.addCuisine(cuisine)) {
			mv.addObject("successMessage", "Cuisine successfully added...");
		} else {
			mv.addObject("errorMessage", "Cuisine adding failed...");
		}
		return mv;	
	}
	
	@RequestMapping("/addRecipeToBranch")
	public ModelAndView addRecipeToBranch() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("isUserClickedAddRecipeToBranch", true);
		mv.addObject("recipeList", urbanspoonService.getRecipes());
		int loggedInUserId = (int) session.getAttribute("loggedInUserId");
		mv.addObject("branchList", urbanspoonService.getBranches(loggedInUserId, false, false, false));
		return mv;
	}
	
	@PostMapping("/addRecipeToBranch")
	public ModelAndView addRecipeToBranch(@RequestParam("branchId") int branchId, @RequestParam("recipeId") int recipeId, @RequestParam("price") double price, @RequestParam("recipeImage") MultipartFile file ) {
		ModelAndView mv = new ModelAndView("home");
		Serve serve = new Serve();
		serve.setBranchId(branchId);
		serve.setRecipeId(recipeId);
		serve.setPrice(price);
		String imagePath = branchId + "_" + recipeId + ".jpg";
		serve.setImage(imagePath);
		FileUtil.upload(IMAGESLOCATION + "recipes", file, imagePath);
		if(urbanspoonService.addRecipeToBranch(serve)) {
			mv.addObject("successMessage", "Recipe successfully added to branch...");
		} else {
			mv.addObject("errorMessage", "Recipe adding failed to branch...");
		}
		return mv;
	}
	
	private List<Restaurant> getAllRestaurants() {
		return urbanspoonService.getAllRestaurants(true, true, true, true);
	}

}
