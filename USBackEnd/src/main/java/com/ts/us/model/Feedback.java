package com.ts.us.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Feedback {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int userId, branchId, feedbackTypeId, recipeId, rating;
	private Date feedbackDate, visitedDate;
	private String comments;
	
	@ManyToOne
	@JoinColumn(name="userId", updatable=false, insertable = false, nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="branchId", updatable=false, insertable = false, nullable = false)
	private Branch branch;
	
	@ManyToOne
	@JoinColumn(name="recipeId", updatable=false, insertable = false, nullable = false)
	private Recipe recipe;
	
	@ManyToOne
	@JoinColumn(name="feedbackTypeId", updatable=false, insertable = false, nullable = false)
	private FeedbackType feedbackType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getFeedbackTypeId() {
		return feedbackTypeId;
	}

	public void setFeedbackTypeId(int feedbackTypeId) {
		this.feedbackTypeId = feedbackTypeId;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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

	public FeedbackType getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(FeedbackType feedbackType) {
		this.feedbackType = feedbackType;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", userId=" + userId + ", branchId=" + branchId + ", feedbackTypeId="
				+ feedbackTypeId + ", recipeId=" + recipeId + ", rating=" + rating + ", feedbackDate=" + feedbackDate
				+ ", visitedDate=" + visitedDate + ", comments=" + comments + "]";
	}

	
}
