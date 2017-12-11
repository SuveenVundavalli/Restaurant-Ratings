package com.ts.us.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ts.us.dto.Recipe;
import com.ts.us.exception.UrbanspoonException;

public class RecipeDAO {

	public Recipe insert(int cuisineId, Recipe recipe) throws UrbanspoonException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into recipe(name,description,cuisine_id, is_veg) values(?,?,?,?)");
			preparedStatement.setString(1, recipe.getName());
			preparedStatement.setString(2, recipe.getDescription());
			preparedStatement.setInt(3, cuisineId);
			System.out.println(recipe.isVeg());
			preparedStatement.setBoolean(4, recipe.isVeg());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(preparedStatement, connection);
		}

		return recipe;
	}

	public List<Recipe> getRecipes() throws UrbanspoonException {

		List<Recipe> recipesList = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("select * from recipe");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				recipesList = new ArrayList<>();
				do {
					Recipe recipe = new Recipe();
					recipe.setId(resultSet.getInt(1));
					recipe.setName(resultSet.getString(2));
					recipe.setVeg(resultSet.getBoolean(4));
					recipesList.add(recipe);
				} while (resultSet.next());
			}

		} catch (SQLException e) {

		} finally {
			DAOUtility.close(resultSet, preparedStatement, connection);
		}

		return recipesList;
	}

	public List<Recipe> getRecipes(int cuisineId) throws UrbanspoonException {

		List<Recipe> recipesList = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = DAOUtility.getConnection();
			preparedStatement = connection
					.prepareStatement("select r.* from recipe r, cuisine c where r.cuisine_id = c.id and c.id = ?");
			preparedStatement.setInt(1, cuisineId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				recipesList = new ArrayList<>();
				do {
					Recipe recipe = new Recipe();
					recipe.setId(resultSet.getInt(1));
					recipe.setName(resultSet.getString(2));
					recipe.setVeg(resultSet.getBoolean(4));
					recipesList.add(recipe);
				} while (resultSet.next());
			}

		} catch (SQLException e) {

		} finally {
			DAOUtility.close(resultSet, preparedStatement, connection);
		}

		return recipesList;
	}

	public Recipe getRecipe(int recipeId) throws UrbanspoonException {

		Recipe recipe = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("select * from recipe where id = ?");
			preparedStatement.setInt(1, recipeId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				recipe = new Recipe();
				recipe.setId(resultSet.getInt(1));
				recipe.setName(resultSet.getString(2));
				recipe.setVeg(resultSet.getBoolean(4));
			}

		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(resultSet, preparedStatement, connection);
		}

		return recipe;
	}

	public List<Recipe> getRecipes(int cuisineId, int branchId) throws UrbanspoonException {

		List<Recipe> recipesList = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement(
					"select r.*,price,image_name from recipe r, cuisine c, serve s where r.cuisine_id = c.id  and r.id = s.recipe_id and c.id = ? and s.branch_id = ?");
			preparedStatement.setInt(1, cuisineId);
			preparedStatement.setInt(2, branchId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				recipesList = new ArrayList<>();
				do {
					Recipe recipe = new Recipe();
					recipe.setId(resultSet.getInt(1));
					recipe.setName(resultSet.getString(2));
					recipe.setDescription(resultSet.getString(3));
					recipe.setVeg(resultSet.getBoolean(5));
					recipe.setPrice(resultSet.getFloat(6));
					recipe.setImage(resultSet.getString(7));
					recipe.setFeedbackList(new FeedbackDAO().getRecipeFeedbacks(recipe.getId(),branchId));
					recipesList.add(recipe);
				} while (resultSet.next());
			}

		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(resultSet, preparedStatement, connection);
		}
		return recipesList;
	}

	public boolean addRecipeToBranch(long recipeId, long branchId, float price, String imagePath)
			throws UrbanspoonException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("insert into serve values(?,?,?,?)");
			preparedStatement.setLong(1, branchId);
			preparedStatement.setLong(2, recipeId);
			preparedStatement.setFloat(3, price);
			preparedStatement.setString(4, imagePath);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(preparedStatement, connection);
		}
		return true;

	}

}
