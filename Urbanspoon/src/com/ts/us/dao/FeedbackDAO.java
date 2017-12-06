package com.ts.us.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ts.us.dto.Branch;
import com.ts.us.dto.Feedback;
import com.ts.us.dto.FeedbackType;
import com.ts.us.dto.Recipe;
import com.ts.us.dto.User;
import com.ts.us.exception.UrbanspoonException;

public class FeedbackDAO {

	public Feedback insertBranchFeedback(Feedback feedback) throws UrbanspoonException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = DAOUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(
					"insert into feedback(user_id,branch_id,feedback_type_id,comments,ratings,visited_date,feedback_date) values(?,?,?,?,?,?,?)");
			preparedStatement.setLong(1, feedback.getUser().getId());
			preparedStatement.setLong(2, feedback.getBranch().getId());
			preparedStatement.setInt(3, feedback.getFeedbackType().getId());
			preparedStatement.setString(4, feedback.getComments());
			preparedStatement.setInt(5, feedback.getRatings());
			preparedStatement.setDate(6, new Date(feedback.getVisitedDate().getTime()));
			preparedStatement.setDate(7, new Date(feedback.getFeedbackDate().getTime()));
			System.out.println("gotit");
			if (preparedStatement.executeUpdate() > 0) {
				feedback.setId(DAOUtility.getLatestId("feedback"));
			}

		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(preparedStatement, connection);
		}

		return feedback;

	}

	public Feedback insertRecipeFeedback(Feedback feedback) throws UrbanspoonException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = DAOUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(
					"insert into feedback(user_id,branch_id,recipe_id,comments,ratings,visited_date,feedback_date) values(?,?,?,?,?,?,?)");
			preparedStatement.setLong(1, feedback.getUser().getId());
			preparedStatement.setLong(2, feedback.getBranch().getId());
			preparedStatement.setInt(3, feedback.getRecipe().getId());
			preparedStatement.setString(4, feedback.getComments());
			preparedStatement.setInt(5, feedback.getRatings());
			preparedStatement.setDate(6, new Date(feedback.getVisitedDate().getTime()));
			preparedStatement.setDate(7, new Date(feedback.getFeedbackDate().getTime()));
			if (preparedStatement.executeUpdate() > 0) {
				feedback.setId(DAOUtility.getLatestId("feedback"));
			}

		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(preparedStatement, connection);
		}

		return feedback;

	}

	public List<Feedback> getRecipeFeedbacks(int recipeId, int branchId) throws UrbanspoonException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = DAOUtility.getConnection();
		ResultSet resultSet = null;
		List<Feedback> feedbacks = null;

		try {
			preparedStatement = connection.prepareStatement("select * from feedback where recipe_id=? and branch_id=?");
			preparedStatement.setInt(1, recipeId);
			preparedStatement.setInt(2, branchId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				feedbacks = new ArrayList<Feedback>();
				do {
					Feedback feedback = new Feedback();
					feedback.setId(resultSet.getInt(1));
					User user = new UserDAO().getUser(resultSet.getInt(2));
					Branch branch = new BranchDAO().getBranch(resultSet.getInt(3), false);
					feedback.setBranch(branch);
					feedback.setUser(user);
					Recipe recipe = new RecipeDAO().getRecipe(resultSet.getInt(5));
					feedback.setRecipe(recipe);
					feedback.setFeedbackDate(resultSet.getDate(6));
					feedback.setVisitedDate(resultSet.getDate(7));
					feedback.setComments(resultSet.getString(8));
					feedback.setRatings(resultSet.getInt(9));
					feedbacks.add(feedback);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return feedbacks;

	}

	public List<Feedback> getBranchFeedbacks(int branchId) throws UrbanspoonException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = DAOUtility.getConnection();
		ResultSet resultSet = null;
		List<Feedback> feedbacks = null;
		try {
			preparedStatement = connection
					.prepareStatement("select * from feedback where branch_id=? and recipe_id is not null");
			preparedStatement.setInt(1, branchId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				feedbacks = new ArrayList<Feedback>();
				do {
					Feedback feedback = new Feedback();
					feedback.setId(resultSet.getInt(1));
					User user = new UserDAO().getUser(resultSet.getInt(2));
					Branch branch = new BranchDAO().getBranch(resultSet.getInt(3), false);
					feedback.setBranch(branch);
					feedback.setUser(user);
					FeedbackType feedbackType = new FeedbackTypeDAO().getFeedbackType(resultSet.getInt(4));
					feedback.setFeedbackType(feedbackType);
					feedback.setFeedbackDate(resultSet.getDate(6));
					feedback.setVisitedDate(resultSet.getDate(7));
					feedback.setComments(resultSet.getString(8));
					feedback.setRatings(resultSet.getInt(9));
					feedbacks.add(feedback);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		}
		return feedbacks;

	}

}
