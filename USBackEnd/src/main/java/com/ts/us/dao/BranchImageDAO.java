package com.ts.us.dao;

import java.util.List;

import com.ts.us.model.BranchImage;

public interface BranchImageDAO {
	boolean save(BranchImage branchImage);	
	boolean update(BranchImage branchImage);	
	boolean delete(int branchId);		
	List<BranchImage> getBranchImages(int branchId);
	
}
