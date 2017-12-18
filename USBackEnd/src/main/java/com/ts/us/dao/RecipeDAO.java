package com.ts.us.dao;

import java.util.List;

import com.ts.us.model.Recipe;

public interface RecipeDAO {
	boolean save(Recipe recipe);	
	boolean update(Recipe recipe);	
	boolean delete(int id);
	Recipe get(int id);
	List<Recipe> getRecipesByCusineId(int cusineId);
	List<Recipe> list();
}
