package com.ts.us.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.us.dao.IRecipeDAO;
import com.ts.us.dto.Recipe;
import com.ts.us.exception.UrbanspoonException;

@Service
public class RecipeService {

	@Autowired IRecipeDAO recipeDAO;
	

	public Recipe insert(int cuisineId, Recipe recipe) throws UrbanspoonException{
		return recipeDAO.insert(cuisineId, recipe);
	}
	public List<Recipe> getRecipes() throws UrbanspoonException{
		return recipeDAO.getRecipes();
	}
	public List<Recipe> getRecipes(int cuisineId) throws UrbanspoonException{
		return recipeDAO.getRecipes(cuisineId);
	}
	public Recipe getRecipe(int recipeId) throws UrbanspoonException{
		return recipeDAO.getRecipe(recipeId);
	}

	public List<Recipe> getRecipes(int cuisineId, int branchId) throws UrbanspoonException{
		return recipeDAO.getRecipes(cuisineId, branchId);
	}

	public boolean addRecipeToBranch(long recipeId, long branchId, float price, String imagePath) throws UrbanspoonException{
		return recipeDAO.addRecipeToBranch(recipeId, branchId, price, imagePath);
	}
	
}
