package com.ts.us.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.us.dao.BranchDAO;
import com.ts.us.dao.BranchImageDAO;
import com.ts.us.dao.CuisineDAO;
import com.ts.us.dao.FeedbackDAO;
import com.ts.us.dao.FeedbackTypeDAO;
import com.ts.us.dao.RecipeDAO;
import com.ts.us.dao.RestaurantDAO;
import com.ts.us.dao.ServeDAO;
import com.ts.us.dao.UserDAO;
import com.ts.us.model.Branch;
import com.ts.us.model.BranchImage;
import com.ts.us.model.Cuisine;
import com.ts.us.model.Feedback;
import com.ts.us.model.FeedbackType;
import com.ts.us.model.Recipe;
import com.ts.us.model.Restaurant;
import com.ts.us.model.Serve;
import com.ts.us.model.User;

@Service
public class UrbanspoonService {

	@Autowired
	private BranchDAO branchDAO;

	@Autowired
	private BranchImageDAO branchImageDAO;

	@Autowired
	private CuisineDAO cuisineDAO;

	@Autowired
	private FeedbackDAO feedbackDAO;

	@Autowired
	private FeedbackTypeDAO feedbackTypeDAO;

	@Autowired
	private RecipeDAO recipeDAO;

	@Autowired
	private RestaurantDAO restaurantDAO;

	@Autowired
	private ServeDAO serveDAO;

	@Autowired
	private UserDAO userDAO;

	// Get all restaurants with branches, recipes and all the feedbacks
	public List<Restaurant> getAllRestaurants(boolean includeBranches, boolean includeBranchFeedback,
			boolean includeCuisines, boolean includeRecipeFeedback) {
		List<Restaurant> restaurants = restaurantDAO.list();
		if (includeBranches) {
			for (Restaurant restaurant : restaurants) {
				restaurant.setBranches(getBranches(restaurant.getId(), includeBranchFeedback, includeCuisines, includeRecipeFeedback));
			}
		}
		System.out.println(restaurants);
		return restaurants;

	}

	public List<Branch> getBranches(int restaurantId, boolean includeBranchFeedback, boolean includeCuisines,
			boolean includeRecipeFeedback) {
		List<Branch> branches = branchDAO.getRestaurantBranches(restaurantId);
		if (includeBranchFeedback) {
			for (Branch branch : branches) {
				branch.setBranchImages(branchImageDAO.getBranchImages(branch.getId()));
				branch.setFeedbacks(feedbackDAO.getBranchFeedbacks(branch.getId()));
				if (includeCuisines) {
					branch.setServes(getBranchCuisine(branch.getId(), includeRecipeFeedback));
				}
			}

		}
		return branches;
	}

	public List<Serve> getBranchCuisine(int branchId, boolean includeRecipeFeedback) {
		Branch branch = branchDAO.get(branchId);
		branch.setServes(serveDAO.getServeByBranch(branchId));
		if (includeRecipeFeedback) {
			for (Serve serve : branch.getServes()) {
				System.out.println("----> Serve " + serve.getId());
				serve.getRecipe().setFeedbacks(feedbackDAO.getRecipeFeedbacks(serve.getRecipeId()));
			}
		}
		return branch.getServes();
	}

	public List<Cuisine> getCuisine(boolean includeRecipes) {
		List<Cuisine> cuisines = cuisineDAO.list();
		if (includeRecipes) {
			for (Cuisine cuisine : cuisines) {
				cuisine.setRecipes(recipeDAO.getRecipesByCusineId(cuisine.getId()));
			}
		}
		return cuisines;
	}

	public List<FeedbackType> getFeedbackTypes() {
		return feedbackTypeDAO.list();
	}

	public Branch getBranch(int branchId, boolean includeCuisines) {
		Branch branch = branchDAO.get(branchId);
		if (includeCuisines) {
			branch.setServes(getBranchCuisine(branchId, true));
		}
		return branch;
	}
	
	public Branch getBranch(String location, int restaurantId) {
		return branchDAO.get(location, restaurantId);
	}

	public Restaurant getRestaurant(int restaurantId, boolean includeBranches) {
		Restaurant restaurant = restaurantDAO.get(restaurantId);
		if (includeBranches) {
			restaurant.setBranches(getBranches(restaurantId, true, true, true));
		}
		return restaurant;
	}
	
	public Restaurant getRestaurant(String govtRegistrationId, boolean includeBranches) {
		Restaurant restaurant = restaurantDAO.get(govtRegistrationId);
		if (includeBranches) {
			restaurant.setBranches(getBranches(restaurant.getId(), true, true, true));
		}
		return restaurant;
	}

	public Recipe getRecipe(int recipeId) {
		return recipeDAO.get(recipeId);
	}

	public List<Recipe> getRecipes() {
		return recipeDAO.list();
	}

	public boolean addUser(User user) {
		return userDAO.save(user);
	}
	
	public boolean addFeedback(Feedback feedback) {
		return feedbackDAO.save(feedback);
	}
	
	public boolean addRecipe(Recipe recipe) {
		return recipeDAO.save(recipe);
	}
	
	public boolean addCuisine(Cuisine cuisine) {
		return cuisineDAO.save(cuisine);
	}
	
	public User getUser(int id) {
		return userDAO.get(id);
	}
	
	public User getUser(String email) {
		return userDAO.get(email);
	}
	
	public boolean register(User user) {
		return userDAO.save(user);
	}
	
	public boolean register(Restaurant restaurant) {
		return restaurantDAO.save(restaurant);
	}
	
	public Restaurant getRestaurant(int id) {
		return restaurantDAO.get(id);
	}
	
	public Restaurant getRestaurant(String govtRegistrationId) {
		return restaurantDAO.get(govtRegistrationId);
	}
	
	public boolean addRecipeToBranch(Serve serve) {
		return serveDAO.save(serve);
	}
	
	public boolean updateRestaurant(Restaurant restaurant) {
		return restaurantDAO.update(restaurant);
	}
	
	public boolean addBranch(Branch branch) {
		return branchDAO.save(branch);
	}
	
	public boolean updateBranch(Branch branch) {
		return branchDAO.update(branch);
	}
	
	public boolean addBranchImage(BranchImage branchImage) {
		return branchImageDAO.save(branchImage);
	}

}






























