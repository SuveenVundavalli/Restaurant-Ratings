package com.ts.us.dao;

import java.util.List;

import com.ts.us.dto.Cuisine;
import com.ts.us.exception.UrbanspoonException;

public interface ICuisineDAO {
	public Cuisine insert(Cuisine cuisine) throws UrbanspoonException;
	public Cuisine getCuisine(int cuisineId, boolean includeRecipes) throws UrbanspoonException;
	public List<Cuisine> getCuisines(boolean includeRecipes) throws UrbanspoonException;
	public List<Cuisine> getCuisines(int branchId, boolean includeRecipes) throws UrbanspoonException;
}
