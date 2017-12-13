package com.ts.us.dao;

import java.util.List;

import com.ts.us.dto.Branch;
import com.ts.us.exception.UrbanspoonException;

public interface IBranchDAO {
	public boolean addImage(long branchId, String fileName) throws UrbanspoonException;
	public Branch insert(long restaurantId, Branch branch) throws UrbanspoonException;
	public List<Branch> getBranches(long restaurantId, boolean includeCuisines,boolean includeFeedbacks) throws UrbanspoonException;
	public Branch getBranch(int branchId, boolean includeCuisines) throws UrbanspoonException;
	public List<String> getBranchImages(int branchId) throws UrbanspoonException;
}
