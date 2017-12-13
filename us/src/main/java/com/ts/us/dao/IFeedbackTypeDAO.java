package com.ts.us.dao;

import java.util.List;

import com.ts.us.dto.FeedbackType;
import com.ts.us.exception.UrbanspoonException;

public interface IFeedbackTypeDAO {
	public List<FeedbackType> getFeedbackTypes() throws UrbanspoonException;
	public FeedbackType getFeedbackType(int feedbackTypeId) throws UrbanspoonException;
	
}
