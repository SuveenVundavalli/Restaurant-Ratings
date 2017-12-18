package com.ts.us.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.us.dao.IBranchDAO;
import com.ts.us.dto.Branch;
import com.ts.us.exception.UrbanspoonException;

@Service
public class BranchService {
	
	@Autowired
	private IBranchDAO branchDAO;

	public boolean addImage(long branchId, String fileName) throws UrbanspoonException {
		return branchDAO.addImage(branchId, fileName);
	}
	public Branch insert(long restaurantId, Branch branch) throws UrbanspoonException {
		return branchDAO.insert(restaurantId, branch);
	}
	public List<Branch> getBranches(long restaurantId, boolean includeCuisines,boolean includeFeedbacks) throws UrbanspoonException{
		return branchDAO.getBranches(restaurantId, includeCuisines, includeFeedbacks);
	}
	public Branch getBranch(int branchId, boolean includeCuisines) throws UrbanspoonException {
		return branchDAO.getBranch(branchId, includeCuisines);
	}
	public List<String> getBranchImages(int branchId) throws UrbanspoonException{
		return branchDAO.getBranchImages(branchId);
	}

}
