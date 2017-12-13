package com.ts.us.dao;

import java.util.List;

import com.ts.us.dto.Recipe;
import com.ts.us.exception.UrbanspoonException;

public interface IRecipeDAO {
	public Recipe insert(int cuisineId, Recipe recipe) throws UrbanspoonException;
	public List<Recipe> getRecipes() throws UrbanspoonException;
	public List<Recipe> getRecipes(int cuisineId) throws UrbanspoonException;
	public Recipe getRecipe(int recipeId) throws UrbanspoonException;
	public List<Recipe> getRecipes(int cuisineId, int branchId) throws UrbanspoonException;
	public boolean addRecipeToBranch(long recipeId, long branchId, float price, String imagePath) throws UrbanspoonException;
}
