package com.ts.us.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ts.us.dto.Cuisine;
import com.ts.us.exception.UrbanspoonException;

public class CuisineDAO {

	public Cuisine insert(Cuisine cuisine) throws UrbanspoonException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("insert into cuisine(name,country) values(?,?)");
			preparedStatement.setString(1, cuisine.getName());
			preparedStatement.setString(2, cuisine.getCountry());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(preparedStatement, connection);
		}
		return cuisine;
	}

	public Cuisine getCuisine(int cuisineId, boolean includeRecipes) throws UrbanspoonException {

		Cuisine cuisine = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("select * from cuisine where id = ?");
			preparedStatement.setInt(1, cuisineId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				cuisine = new Cuisine();
				cuisine.setId(resultSet.getInt(1));
				cuisine.setName(resultSet.getString(2));
				cuisine.setCountry(resultSet.getString(3));
				if (includeRecipes) {
					cuisine.setRecipesList(new RecipeDAO().getRecipes(cuisineId));
				}
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(preparedStatement, connection);
		}
		return cuisine;
	}

	public List<Cuisine> getCuisines(boolean includeRecipes) throws UrbanspoonException {

		List<Cuisine> cuisinesList = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("select * from cuisine");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				cuisinesList = new ArrayList<Cuisine>();
				do {
					Cuisine cuisine = new Cuisine();
					cuisine.setId(resultSet.getInt(1));
					cuisine.setName(resultSet.getString(2));
					cuisine.setCountry(resultSet.getString(3));
					if (includeRecipes) {
						cuisine.setRecipesList(new RecipeDAO().getRecipes(cuisine.getId()));
					}
					cuisinesList.add(cuisine);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(resultSet, preparedStatement, connection);
		}
		return cuisinesList;
	}

	public List<Cuisine> getCuisines(int branchId, boolean includeRecipes) throws UrbanspoonException {

		List<Cuisine> cuisinesList = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement(
					"select distinct c.* from cuisine c, recipe r, serve s where c.id = r.cuisine_id and r.id = s.recipe_id and s.branch_id = ?");
			preparedStatement.setInt(1, branchId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				cuisinesList = new ArrayList<Cuisine>();
				do {
					Cuisine cuisine = new Cuisine();
					cuisine.setId(resultSet.getInt(1));
					cuisine.setName(resultSet.getString(2));
					cuisine.setCountry(resultSet.getString(3));
					if (includeRecipes) {
						cuisine.setRecipesList(new RecipeDAO().getRecipes(cuisine.getId(),branchId));
					}
					cuisinesList.add(cuisine);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(resultSet, preparedStatement, connection);
		}

		return cuisinesList;

	}

}
