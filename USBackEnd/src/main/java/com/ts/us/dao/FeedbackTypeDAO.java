package com.ts.us.dao;

import java.util.List;

import com.ts.us.model.FeedbackType;

public interface FeedbackTypeDAO {
	boolean save(FeedbackType feedbackType);	
	boolean update(FeedbackType feedbackType);	
	boolean delete(int id);	
	FeedbackType get(int id);	
	List<FeedbackType> list();
	
}
