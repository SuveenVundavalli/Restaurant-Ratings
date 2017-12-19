package com.ts.us.dao;

import java.util.List;

import com.ts.us.model.Branch;

public interface BranchDAO {
	boolean save(Branch branch);	
	boolean update(Branch branch);	
	boolean delete(int branchId);
	Branch get(String location, int restaurantId);
	Branch get(int branchId);	
	List<Branch> getRestaurantBranches(int restaurantId);	
	List<Branch> list();	
}
