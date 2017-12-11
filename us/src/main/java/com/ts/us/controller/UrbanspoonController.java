package com.ts.us.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ts.us.dao.BranchDAO;
import com.ts.us.dao.CuisineDAO;
import com.ts.us.dao.FeedbackDAO;
import com.ts.us.dao.FeedbackTypeDAO;
import com.ts.us.dao.RecipeDAO;
import com.ts.us.dao.RestaurantDAO;
import com.ts.us.dao.UserDAO;
import com.ts.us.dto.Branch;
import com.ts.us.dto.Cuisine;
import com.ts.us.dto.Feedback;
import com.ts.us.dto.FeedbackType;
import com.ts.us.dto.Recipe;
import com.ts.us.dto.Restaurant;
import com.ts.us.dto.User;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.helper.UrbanspoonHelper;
import com.ts.us.util.DateUtility;

@Controller
public class UrbanspoonController {

	// Mac Path
	private static final String IMAGESLOCATION = "//Users//suveen//Documents//EclipseWorkspace//Restaurant-Ratings//us//src//main//webapp//images";
	// Linux Path
	// private static final String IMAGESLOCATION =
	// "//home//tsuser//Desktop//TalentSprint-Project-Suveen//Restaurant-Ratings//Urbanspoon//WebContent//images";

	@RequestMapping("/home")
	public ModelAndView goToHome() {
		ModelAndView mv = null;
		try {
			mv = new ModelAndView("home");
			RestaurantDAO restaurantDAO = new RestaurantDAO();
			List<Restaurant> restaurantsList = restaurantDAO.getRestaurants(true);
			mv.addObject("user", new User());
			mv.addObject("restaurantsList", restaurantsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam("user_id") String user_id, @RequestParam("password") String password,
			@RequestParam("loginAs") String loginAs, HttpServletRequest request) throws UrbanspoonException {
		HttpSession session = request.getSession();
		ModelAndView mv = null;
		if (loginAs != null && loginAs.equals("user")) {
			UserDAO userDAO = new UserDAO();
			User user = userDAO.getUser(user_id);
			if (user != null && user.getPassword().equals(password)) {
				session.setAttribute("loggedInUser", user);
				session.setAttribute("loggedInUserId", user.getId());
				mv = new ModelAndView("userHome");
				mv.addObject("restaurantsList", new RestaurantDAO().getRestaurants(true));
			}
		} else if (loginAs != null && loginAs.equals("restaurant")) {
			RestaurantDAO restaurantDAO = new RestaurantDAO();
			Restaurant restaurant = restaurantDAO.getRestaurant(user_id, false);
			if (restaurant != null && restaurant.getPassword().equals(password)) {
				session.setAttribute("loggedInUser", restaurant);
				session.setAttribute("loggedInUserId", restaurant.getId());
				mv = new ModelAndView("restaurantHome");
				mv = addRestaurantObjects(mv, request);
			}
		} else {
			mv = new ModelAndView("home");
			RestaurantDAO restaurantDAO = new RestaurantDAO();
			List<Restaurant> restaurantsList = restaurantDAO.getRestaurants(true);
			mv.addObject("restaurantsList", restaurantsList);
		}

		return mv;
	}

	@PostMapping("/user_registration")
	public ModelAndView registerUser(@RequestParam("first_name") String firstName,
			@RequestParam("last_name") String lastName, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("gender") String gender,
			@RequestParam("mobile_number") String mobileNumber) throws UrbanspoonException {
		ModelAndView mv = new ModelAndView("/home");
		User user = new User();
		user.setName(firstName + " " + lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setMobileNumber(Long.parseLong(mobileNumber));
		user.setGender(gender);
		UserDAO userDAO = new UserDAO();
		user = userDAO.insert(user);
		return mv;
	}

	@PostMapping("/user_registration_spring")
	public ModelAndView springRegisterUser(@ModelAttribute("user") User user) throws UrbanspoonException{
		UserDAO userDAO = new UserDAO();
		user = userDAO.insert(user);
		ModelAndView mv = new ModelAndView("redirect:home");
		return mv;
	}

	@PostMapping("/restaurant_registration")
	public ModelAndView restaurantRegister(HttpServletRequest request) throws UrbanspoonException, FileUploadException {
		ModelAndView mv = new ModelAndView("redirect:home");
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		List<FileItem> fileItemsList = servletFileUpload.parseRequest(request);
		Restaurant restaurant = new Restaurant();
		for (FileItem fileItem : fileItemsList) {
			if (fileItem.isFormField()) {
				if (fileItem.getFieldName().equals("govt_registration_id")) {
					restaurant.setGovtRegistrationtId(fileItem.getString());
				} else if (fileItem.getFieldName().equals("name")) {
					restaurant.setName(fileItem.getString());
				} else if (fileItem.getFieldName().equals("password")) {
					restaurant.setPassword(fileItem.getString());
				}
			}
		}
		RestaurantDAO restaurantDAO = new RestaurantDAO();
		restaurant = restaurantDAO.insert(restaurant);
		if (restaurant.getId() != 0) {
			for (FileItem fileItem : fileItemsList) {
				if (!fileItem.isFormField()) {
					storeImage(fileItem, "restaurants", restaurant.getId() + ".jpg");
					restaurantDAO.updateLogoAddress(restaurant.getId(), restaurant.getId() + ".jpg");
				}
			}
		}
		return mv;
	}

	// FeedBacks
	//Loading branch feedback form
	@RequestMapping("/branch_feedback")
	public ModelAndView addBranchFeedback(@RequestParam("branch_id") int branchId,
			@RequestParam("restaurant_id") int restaurantId, HttpServletRequest request) {
		ModelAndView mv = null;
		long loggedInUserId = 0;
		HttpSession session = request.getSession(false);
		if (session != null) {
			loggedInUserId = (long) session.getAttribute("loggedInUserId");
		}
		if (loggedInUserId != 0) {
			mv = new ModelAndView("userHome");
			try {
				mv.addObject("branch", new BranchDAO().getBranch(branchId, false));
				mv.addObject("restaurant", new RestaurantDAO().getRestaurant(restaurantId, false));
				mv.addObject("feedbackTypeList", new FeedbackTypeDAO().getFeedbackTypes());
			} catch (UrbanspoonException e) {
				e.printStackTrace();
			}

			mv.addObject("feedback", new Feedback());
		} else {
			mv = new ModelAndView("home");
			RestaurantDAO restaurantDAO = new RestaurantDAO();
			List<Restaurant> restaurantsList = null;

			try {
				restaurantsList = restaurantDAO.getRestaurants(true);
				mv.addObject("user", new User());
				mv.addObject("restaurantsList", restaurantsList);
			} catch (UrbanspoonException e) {
				e.printStackTrace();
			}

		}
		return mv;
	}

	//Submitting branch feedback
	@RequestMapping(value = "/add_branch_feedback", method = RequestMethod.POST)
	public ModelAndView addNewBranchFeedback(@RequestParam Map<String, String> requestParams,
			HttpServletRequest request) throws UrbanspoonException {
		ModelAndView mv = new ModelAndView("userHome");
		Feedback feedback = new Feedback();
		Branch branch = new Branch();
		branch.setId(Integer.parseInt(requestParams.get("branch_id")));
		User user = new User();
		user.setId(getLoggedUserId(request));
		feedback.setBranch(branch);
		feedback.setUser(user);
		FeedbackType feedbackType = new FeedbackType();
		feedbackType.setId(Integer.parseInt(requestParams.get("feedback_type_id")));
		feedback.setFeedbackType(feedbackType);
		feedback.setComments(requestParams.get("comments"));
		feedback.setRatings(Integer.parseInt(requestParams.get("rating")));
		System.out.println(requestParams.get("visited_Date"));
		feedback.setVisitedDate(DateUtility.convertStringToDate(requestParams.get("visited_Date")));
		feedback.setFeedbackDate(new Date());
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		feedback = feedbackDAO.insertBranchFeedback(feedback);
		mv.addObject("restaurantsList", new RestaurantDAO().getRestaurants(true));
		return mv;
	}
	
	//Loading Recipe feedback form
	@RequestMapping("/recipe_feedback")
	public ModelAndView addRecipeFeedback(@RequestParam("recipe_id") int recipeId,
			@RequestParam("branch_id") int branchId, @RequestParam("restaurant_id") int restaurantId)
			throws UrbanspoonException {
		ModelAndView mv = new ModelAndView("userHome");
		mv.addObject("restaurant", new RestaurantDAO().getRestaurant(restaurantId, false));
		mv.addObject("branch", new BranchDAO().getBranch(branchId, false));
		mv.addObject("recipe", new RecipeDAO().getRecipe(recipeId));
		return mv;
	}
	
	//Submitting recipe feedback
	@PostMapping("/new_recipe_feedback")
	public ModelAndView addNewRecipeFeedback(@RequestParam Map<String, String> reqParams, HttpServletRequest request)
			throws UrbanspoonException {
		ModelAndView mv = new ModelAndView("userHome");
		Feedback feedback = new Feedback();
		Branch branch = new Branch();
		branch.setId(Integer.parseInt(reqParams.get("branch_id")));
		User user = new User();
		user.setId(getLoggedUserId(request));
		feedback.setBranch(branch);
		feedback.setUser(user);
		FeedbackType feedbackType = new FeedbackType();
		feedback.setFeedbackType(feedbackType);
		Recipe recipe = new Recipe();
		recipe.setId(Integer.parseInt(reqParams.get("recipe_id")));
		feedback.setRecipe(recipe);
		feedback.setComments(reqParams.get("comments"));
		feedback.setRatings(Integer.parseInt(reqParams.get("rating")));
		feedback.setVisitedDate(DateUtility.convertStringToDate(reqParams.get("visited_Date")));
		feedback.setFeedbackDate(new Date());
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		feedback = feedbackDAO.insertRecipeFeedback(feedback);
		mv.addObject("restaurantsList", new RestaurantDAO().getRestaurants(true));
		return mv;
	}
	
	//Restaurant Operations
	@PostMapping("/branch")
	public ModelAndView addBranch(HttpServletRequest request) throws UrbanspoonException {
		List<FileItem> fileItemsList = UrbanspoonHelper.getFileItems(request);

		Branch branch = new Branch();
		for (FileItem fileItem : fileItemsList) {
			if (fileItem.isFormField()) {
				if (fileItem.getFieldName().equals("location")) {
					branch.setLocation(fileItem.getString());
				}
				if (fileItem.getFieldName().equals("city")) {
					branch.setCity(fileItem.getString());
				}
				if (fileItem.getFieldName().equals("state")) {
					branch.setState(fileItem.getString());
				}
				if (fileItem.getFieldName().equals("country")) {
					branch.setCountry(fileItem.getString());
				}
				if (fileItem.getFieldName().equals("postal_code")) {
					branch.setPostalCode(Integer.parseInt(fileItem.getString()));
				}
			}
		}
		System.out.println(branch);
		BranchDAO branchDAO = new BranchDAO();
		branch = branchDAO.insert(getLoggedUserId(request), branch);
		if (branch.getId() != 0) {
			int count = 0;
			for (FileItem fileItem : fileItemsList) {
				if (!fileItem.isFormField()) {
					++count;
					storeImage(fileItem, "branches", branch.getId() + "_" + count + ".jpg");
					branchDAO.addImage(branch.getId(), branch.getId() + "_" + count + ".jpg");
				}
			}
		}
		ModelAndView mv = new ModelAndView("restaurantHome");
		mv = addRestaurantObjects(mv, request);
		return mv;
	}
	
	@PostMapping("/cuisine")
	public ModelAndView addCuisine(@RequestParam("name") String name, @RequestParam("country") String country) throws UrbanspoonException {
		Cuisine cuisine = new Cuisine();
		cuisine.setName(name);
		cuisine.setCountry(country);
		CuisineDAO cuisineDAO = new CuisineDAO();
		cuisine = cuisineDAO.insert(cuisine);
		ModelAndView mv = new ModelAndView("restaurantHome");
		return mv;
	}
	
	@PostMapping("/recipe")
	public ModelAndView addRecipe(@RequestParam Map<String, String> requestParams, HttpServletRequest request) throws UrbanspoonException {
		Recipe recipe = new Recipe();
		recipe.setName(requestParams.get("recipe_name"));
		recipe.setDescription(requestParams.get("description"));
		String recipeType = requestParams.get("recipe_type");
		if (recipeType.equals("Veg")) {
			recipe.setVeg(true);
		} else {
			recipe.setVeg(false);
		}
		int cuisineId = Integer.parseInt(requestParams.get("cuisine_id"));
		RecipeDAO recipeDAO = new RecipeDAO();
		recipe = recipeDAO.insert(cuisineId, recipe);
		ModelAndView mv = new ModelAndView("restaurantHome");
		mv = addRestaurantObjects(mv, request);
		return mv;
	}
	
	@PostMapping("/recipe_to_branch")
	public ModelAndView addRecipeToBranch(HttpServletRequest request) throws UrbanspoonException {
		List<FileItem> fileItemsList = UrbanspoonHelper.getFileItems(request);

		long branchId = 0;
		long recipeId = 0;
		float price = 0;
		String imagePath = null;
		for (FileItem fileItem : fileItemsList) {
			if (fileItem.isFormField()) {
				if (fileItem.getFieldName().equals("recipe_id")) {
					recipeId = Long.parseLong(fileItem.getString());
				}
				if (fileItem.getFieldName().equals("branch_id")) {
					branchId = Long.parseLong(fileItem.getString());
				}
				if (fileItem.getFieldName().equals("price")) {
					price = Float.parseFloat(fileItem.getString());
				}
			}
		}
		for (FileItem fileItem : fileItemsList) {
			if (!fileItem.isFormField()) {
				imagePath = branchId + "_" + recipeId + ".jpg";
				storeImage(fileItem, "recipes", imagePath);
			}
		}
		
		RecipeDAO recipeDAO = new RecipeDAO();
		recipeDAO.addRecipeToBranch(recipeId, branchId, price, imagePath);
		ModelAndView mv = new ModelAndView("restaurantHome");
		mv = addRestaurantObjects(mv, request);
		return mv;
		
	}
	
	private long getLoggedUserId(HttpServletRequest request) {
		return (long) request.getSession(false).getAttribute("loggedInUserId");
	}

	private static boolean storeImage(FileItem fileItem, String imageType, String fileName) throws UrbanspoonException {
		if (null != fileItem) {
			try {
				String filePath = IMAGESLOCATION + "//" + imageType + "//" + fileName;
				fileItem.write(new File(filePath));
				return true;
			} catch (Exception e) {
				throw new UrbanspoonException(e.toString());
			}
		}
		return false;
	}
	
	private ModelAndView addRestaurantObjects(ModelAndView mv, HttpServletRequest request) throws UrbanspoonException {
		mv.addObject("cuisineList", new CuisineDAO().getCuisines(false));
		mv.addObject("branchList", new BranchDAO().getBranches(getLoggedUserId(request) , true, true));
		mv.addObject("recipeList", new RecipeDAO().getRecipes());
		return mv;
		
	}
}
