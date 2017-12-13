package com.ts.us.dao;

import java.util.List;

import com.ts.us.dto.Feedback;
import com.ts.us.exception.UrbanspoonException;

public interface IFeedbackDAO {
	public Feedback insertBranchFeedback(Feedback feedback) throws UrbanspoonException;
	public Feedback insertRecipeFeedback(Feedback feedback) throws UrbanspoonException;
	public List<Feedback> getRecipeFeedbacks(int recipeId, int branchId) throws UrbanspoonException;
	public List<Feedback> getBranchFeedbacks(int branchId) throws UrbanspoonException;
	
}
