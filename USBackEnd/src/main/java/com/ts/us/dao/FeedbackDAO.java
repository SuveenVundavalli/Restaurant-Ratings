package com.ts.us.dao;

import java.util.List;

import com.ts.us.model.Feedback;

public interface FeedbackDAO {
	boolean save(Feedback feedback);	
	boolean update(Feedback feedback);	
	boolean delete(int id);
	Feedback get(int id);
	List<Feedback> getBranchFeedbacks(int branchId);	
	List<Feedback> getRecipeFeedbacks(int recipeId);	
	List<Feedback> list();	
}
