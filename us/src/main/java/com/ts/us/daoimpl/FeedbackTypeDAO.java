package com.ts.us.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ts.us.dao.IFeedbackTypeDAO;
import com.ts.us.dto.FeedbackType;
import com.ts.us.exception.UrbanspoonException;

@Component
public class FeedbackTypeDAO implements IFeedbackTypeDAO{
	public List<FeedbackType> getFeedbackTypes() throws UrbanspoonException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<FeedbackType> feedbackTypes = null;
		try {
			connection = DAOUtility.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from feedback_type");
			if (resultSet.next()) {
				feedbackTypes = new ArrayList<FeedbackType>();
				do {
					FeedbackType feedbackType = new FeedbackType();
					feedbackType.setId(resultSet.getInt(1));
					feedbackType.setDescription(resultSet.getString(2));
					feedbackTypes.add(feedbackType);
				} while (resultSet.next());

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return feedbackTypes;

	}

	public FeedbackType getFeedbackType(int feedbackTypeId) throws UrbanspoonException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		FeedbackType feedbackType = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("select * from feedback_type where id=?");
			preparedStatement.setInt(1, feedbackTypeId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				feedbackType = new FeedbackType();
				feedbackType.setId(resultSet.getInt(1));
				feedbackType.setDescription(resultSet.getString(2));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return feedbackType;

	}

}
