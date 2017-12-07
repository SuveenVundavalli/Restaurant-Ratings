package com.ts.us.helper;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
import com.ts.us.util.DateUtility;

public class UrbanspoonHelper {
	//Mac Path
	//private static final String IMAGESLOCATION = "//Users//suveen//Documents//EclipseWorkspace//Restaurant-Ratings//Urbanspoon//WebContent//images";
	// Linux Path
	private static final String IMAGESLOCATION = "//home//tsuser//Desktop//TalentSprint-Project-Suveen//Restaurant-Ratings//Urbanspoon//WebContent//images";

	public static long getLoggedUserId(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(true);
		Object object = httpSession.getAttribute("loggedUserId");
		if (null != object)
			return (long) object;
		return -1;
	}

	public static List<Restaurant> getRestaurants(boolean includeBranches) throws UrbanspoonException {
		return new RestaurantDAO().getRestaurants(includeBranches);
	}

	public static List<Branch> getBranches(HttpServletRequest request, boolean includeCuisines)
			throws UrbanspoonException {
		return new BranchDAO().getBranches(getLoggedUserId(request), includeCuisines, true);
	}

	public static List<Cuisine> getCuisine(boolean includeRecipe) throws UrbanspoonException {
		return new CuisineDAO().getCuisines(includeRecipe);
	}

	public static List<FeedbackType> getFeedbackTypesList() throws UrbanspoonException {
		return new FeedbackTypeDAO().getFeedbackTypes();
	}

	public static Branch getBranch(int branchId, boolean includeCuisines) throws UrbanspoonException {
		return new BranchDAO().getBranch(branchId, includeCuisines);
	}

	public static Restaurant getRestaurant(int restaurantId, boolean includeBranches) throws UrbanspoonException {
		return new RestaurantDAO().getRestaurant(restaurantId, includeBranches);
	}

	public static Recipe getRecipe(int recipeId) throws UrbanspoonException {
		return new RecipeDAO().getRecipe(recipeId);
	}
	public static List<Recipe> getRecipes() throws UrbanspoonException {
		return new RecipeDAO().getRecipes();
	}

	public static List<FileItem> getFileItems(HttpServletRequest request) throws UrbanspoonException {
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		try {
			return servletFileUpload.parseRequest(request);
		} catch (FileUploadException e) {
			throw new UrbanspoonException(e.toString());
		}
	}

	public static String getFormFeildValue(List<FileItem> fileItemsList, String fieldName) throws UrbanspoonException {
		if (fileItemsList != null) {
			for (FileItem fileItem : fileItemsList) {
				if (fileItem.getFieldName().equals(fieldName)) {
					return fileItem.getString();
				}
			}
		}
		return null;
	}

	public static boolean addRestaurant(List<FileItem> fileItemsList, HttpServletRequest request,
			HttpServletResponse response) throws UrbanspoonException {

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

				storeImage(fileItem, "restaurants", restaurant.getId() + ".jpg");
				restaurantDAO.updateLogoAddress(restaurant.getId(), restaurant.getId() + ".jpg");
			}
			return true;
		}
		return false;
	}

	public static boolean addBranch(List<FileItem> fileItemsList, HttpServletRequest request,
			HttpServletResponse response) throws UrbanspoonException {
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
			return true;
		}
		return false;
	}

	public static boolean addRecipeToBranch(List<FileItem> fileItemsList, HttpServletRequest request,
			HttpServletResponse response) throws UrbanspoonException {
		long branchId = 0;
		long recipeId = 0;
		float price = 0;
		String imagePath = null;
		for (FileItem fileItem : fileItemsList) {
			if (fileItem.isFormField()) {
				//System.out.println(fileItem);
				if (fileItem.getFieldName().equals("recipe_id")) {
					recipeId = Long.parseLong(fileItem.getString());
				}
				if (fileItem.getFieldName().equals("branch_id")) {
					branchId = Long.parseLong(fileItem.getString());
				}
				if (fileItem.getFieldName().equals("price")) {
					price = Float.parseFloat(fileItem.getString());
				}
				//System.out.println("RecipeId : "+recipeId+" Branch Id "+branchId+" Price "+price);
			}
		}
		for (FileItem fileItem : fileItemsList) {
			if (!fileItem.isFormField()) {
				imagePath = branchId + "_" + recipeId + ".jpg";
				storeImage(fileItem, "recipes", imagePath);
				
			
			}
		}
		
		RecipeDAO recipeDAO = new RecipeDAO();
		if (recipeDAO.addRecipeToBranch(recipeId, branchId, price, imagePath)) {
			return true;
		}
		return false;
	}

	public static boolean addUser(HttpServletRequest request, HttpServletResponse response) throws UrbanspoonException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		long mobileNumber = Long.parseLong(request.getParameter("mobile_number"));

		User user = new User();
		user.setName(firstName + " " + lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setMobileNumber(mobileNumber);
		user.setGender(gender);
		UserDAO userDAO = new UserDAO();
		user = userDAO.insert(user);
		if (user.getId() != 0) {
			return true;
		}
		return false;
	}

	public static boolean addBranchFeedback(HttpServletRequest request, HttpServletResponse response)
			throws UrbanspoonException {
		Feedback feedback = new Feedback();
		Branch branch = new Branch();
		branch.setId(Integer.parseInt(request.getParameter("branch_id")));
		User user = new User();
		user.setId(getLoggedUserId(request));
		feedback.setBranch(branch);
		feedback.setUser(user);
		FeedbackType feedbackType = new FeedbackType();
		feedbackType.setId(Integer.parseInt(request.getParameter("feedback_type_id")));
		feedback.setFeedbackType(feedbackType);
		feedback.setComments(request.getParameter("comments"));
		feedback.setRatings(Integer.parseInt(request.getParameter("rating")));
		System.out.println(request.getParameter("visited_Date"));
		feedback.setVisitedDate(DateUtility.convertStringToDate(request.getParameter("visited_Date")));
		feedback.setFeedbackDate(new Date());
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		feedback = feedbackDAO.insertBranchFeedback(feedback);
		if (feedback.getId() != 0) {
			return true;
		}
		return false;
	}

	public static boolean addRecipeFeedback(HttpServletRequest request, HttpServletResponse response)
			throws UrbanspoonException {
		Feedback feedback = new Feedback();
		Branch branch = new Branch();
		branch.setId(Integer.parseInt(request.getParameter("branch_id")));
		User user = new User();
		user.setId(getLoggedUserId(request));
		feedback.setBranch(branch);
		feedback.setUser(user);
		FeedbackType feedbackType = new FeedbackType();
		feedback.setFeedbackType(feedbackType);
		Recipe recipe = new Recipe();
		recipe.setId(Integer.parseInt(request.getParameter("recipe_id")));
		feedback.setRecipe(recipe);
		feedback.setComments(request.getParameter("comments"));
		feedback.setRatings(Integer.parseInt(request.getParameter("rating")));
		feedback.setVisitedDate(DateUtility.convertStringToDate(request.getParameter("visited_Date")));
		feedback.setFeedbackDate(new Date());
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		feedback = feedbackDAO.insertRecipeFeedback(feedback);
		if (feedback.getId() != 0) {
			return true;
		}
		return false;
	}

	public static boolean addRecipe(HttpServletRequest request, HttpServletResponse response)
			throws UrbanspoonException {

		Recipe recipe = new Recipe();
		recipe.setName(request.getParameter("recipe_name"));
		recipe.setDescription(request.getParameter("description"));
		String recipeType = request.getParameter("recipe_type");
		if (recipeType.equals("Veg")) {
			recipe.setVeg(true);
		} else {
			recipe.setVeg(false);
		}
		int cuisineId = Integer.parseInt(request.getParameter("cuisine_id"));
		RecipeDAO recipeDAO = new RecipeDAO();
		recipe = recipeDAO.insert(cuisineId, recipe);
		if (recipe.getId() != 0) {
			return true;
		}
		return false;
	}

	public static boolean addCuisine(HttpServletRequest request, HttpServletResponse response)
			throws UrbanspoonException {
		Cuisine cuisine = new Cuisine();
		cuisine.setName(request.getParameter("cuisine_name"));
		cuisine.setCountry(request.getParameter("country"));
		CuisineDAO cuisineDAO = new CuisineDAO();
		cuisine = cuisineDAO.insert(cuisine);
		if (cuisine.getId() != 0) {
			return true;
		}
		return false;
	}

	public static boolean storeImage(FileItem fileItem, String imageType, String fileName) throws UrbanspoonException {
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

	public static boolean loginAsUser(HttpServletRequest request, HttpServletResponse response)
			throws UrbanspoonException {
		String email = request.getParameter("user_id");
		String password = request.getParameter("password");
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(email);
		if (null != user && password.equals(user.getPassword())) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("loggedUserId", user.getId());
			return true;
		}
		return false;
	}

	public static boolean loginAsRestaurantOwner(HttpServletRequest request, HttpServletResponse response)
			throws UrbanspoonException {
		String govtRegistrationId = request.getParameter("user_id");
		String password = request.getParameter("password");
		RestaurantDAO restaurantDAO = new RestaurantDAO();
		Restaurant restaurant = restaurantDAO.getRestaurant(govtRegistrationId, false);
		System.out.println(restaurant);
		if (null != restaurant && password.equals(restaurant.getPassword())) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("loggedUserId", restaurant.getId());
			return true;
		}
		return false;
	}
}
