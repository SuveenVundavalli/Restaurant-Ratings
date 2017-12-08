package com.ts.us.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ts.us.dto.User;
import com.ts.us.exception.UrbanspoonException;

public class UserDAO {

	public User insert(User user) throws UrbanspoonException {
		Connection connection = DAOUtility.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement("insert into user(name,gender,email,password,mobile_number) values(?,?,?,?,?)");
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getGender());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setLong(5, user.getMobileNumber());
			if (preparedStatement.executeUpdate() > 0) {
				user.setId(DAOUtility.getLatestId("user"));
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(preparedStatement, connection);
		}
		return user;
	}

	public User getUser(String email) throws UrbanspoonException {
		User user = null;
		Connection connection = DAOUtility.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement("select * from user where email=?");
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setGender(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setPassword(resultSet.getString(5));
				user.setMobileNumber(resultSet.getLong(6));
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(resultSet, preparedStatement, connection);
		}
		return user;
	}

	public User getUser(int userId) throws UrbanspoonException {
		User user = null;
		Connection connection = DAOUtility.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement("select * from user where id=?");
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setGender(resultSet.getString(3));
				user.setEmail(resultSet.getString(4));
				user.setPassword(resultSet.getString(5));
				user.setMobileNumber(resultSet.getLong(6));
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(resultSet, preparedStatement, connection);
		}
		return user;
	}
}
