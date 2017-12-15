package com.ts.us.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ts.us.dao.IRestaurantDAO;
import com.ts.us.dto.Restaurant;
import com.ts.us.exception.UrbanspoonException;

//@Component
public class RestaurantDAO implements IRestaurantDAO{

	public List<Restaurant> getRestaurants(boolean includeBranches) throws UrbanspoonException {

		List<Restaurant> restaurantsList = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DAOUtility.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from restaurant");
			if (resultSet.next()) {
				restaurantsList = new ArrayList<>();
				do {
					Restaurant restaurant = new Restaurant();
					restaurant.setId(resultSet.getInt(1));
					restaurant.setGovtRegistrationtId(resultSet.getString(2));
					restaurant.setName(resultSet.getString(3));
					restaurant.setLogo(resultSet.getString(5));

					if (includeBranches) {
						restaurant.setBranchesList(new BranchDAO().getBranches(restaurant.getId(), true,true));
					}
					restaurantsList.add(restaurant);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(resultSet, statement, connection);

		}
		return restaurantsList;
	}

	public Restaurant getRestaurant(int restaurantId, boolean includeBranches) throws UrbanspoonException {
		Restaurant restaurant = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("select * from restaurant where id = ?");
			preparedStatement.setInt(1, restaurantId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				restaurant = new Restaurant();
				restaurant.setId(resultSet.getInt(1));
				restaurant.setGovtRegistrationtId(resultSet.getString(2));
				restaurant.setName(resultSet.getString(3));
				restaurant.setLogo(resultSet.getString(5));
				if (includeBranches) {
					restaurant.setBranchesList(new BranchDAO().getBranches(restaurantId, true,true));
				}
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(resultSet, preparedStatement, connection);
		}
		return restaurant;
	}

	public Restaurant insert(Restaurant restaurant) throws UrbanspoonException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into restaurant(govt_registration_id,name,password) values(?,?,?)");
			preparedStatement.setString(1, restaurant.getGovtRegistrationtId());
			preparedStatement.setString(2, restaurant.getName());
			preparedStatement.setString(3, restaurant.getPassword());
			if (preparedStatement.executeUpdate() > 0) {
				restaurant.setId(DAOUtility.getLatestId("restaurant"));
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(resultSet, preparedStatement, connection);

		}
		return restaurant;
	}

	public Restaurant getRestaurant(String govtRegistrationId, boolean includeBranches) throws UrbanspoonException {
		Restaurant restaurant = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("select * from restaurant where govt_registration_id = ?");
			preparedStatement.setString(1, govtRegistrationId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				restaurant = new Restaurant();
				restaurant.setId(resultSet.getInt(1));
				restaurant.setGovtRegistrationtId(resultSet.getString(2));
				restaurant.setName(resultSet.getString(3));
				restaurant.setPassword(resultSet.getString(4));
				restaurant.setLogo(resultSet.getString(5));
				if (includeBranches) {
					restaurant.setBranchesList(new BranchDAO().getBranches(resultSet.getInt(1), true,false));
				}
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(resultSet, preparedStatement, connection);
		}
		return restaurant;
	}

	public boolean updateLogoAddress(long restaurantId, String fileName) throws UrbanspoonException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("update restaurant set logo_name =? where id = ?");
			preparedStatement.setString(1, fileName);
			preparedStatement.setLong(2, restaurantId);

			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(preparedStatement, connection);

		}
		return false;
	}

}
