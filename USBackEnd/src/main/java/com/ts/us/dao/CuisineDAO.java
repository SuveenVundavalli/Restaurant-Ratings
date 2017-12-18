package com.ts.us.dao;

import java.util.List;

import com.ts.us.model.Cuisine;

public interface CuisineDAO {
	boolean save(Cuisine cuisine);	
	boolean update(Cuisine cuisine);	
	boolean delete(int id);
	Cuisine get(int id);	
	List<Cuisine> list();	
}
