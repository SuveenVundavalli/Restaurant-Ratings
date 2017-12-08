package com.ts.us.dto;

import java.util.Date;

public class Feedback {

	private int id;
	private Date feedbackDate;
	private Date visitedDate;
	private String comments;
	private int ratings;
	private FeedbackType feedbackType;
	private User user;
	private Branch branch;
	private Recipe recipe;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFeedbackDate() {
		return feedbackDate;
	}
	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}
	public Date getVisitedDate() {
		return visitedDate;
	}
	public void setVisitedDate(Date visitedDate) {
		this.visitedDate = visitedDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public FeedbackType getFeedbackType() {
		return feedbackType;
	}
	public void setFeedbackType(FeedbackType feedbackType) {
		this.feedbackType = feedbackType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	@Override
	public String toString() {
		return "Feedback [id=" + id + ", feedbackDate=" + feedbackDate + ", visitedDate=" + visitedDate + ", comments="
				+ comments + ", ratings=" + ratings + ", feedbackType=" + feedbackType + ", user=" + user + ", branch="
				+ branch + ", recipe=" + recipe + "]";
	}
	
}
