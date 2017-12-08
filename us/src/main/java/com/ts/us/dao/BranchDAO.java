package com.ts.us.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ts.us.dto.Branch;
import com.ts.us.exception.UrbanspoonException;

public class BranchDAO {

	public boolean addImage(long branchId, String fileName) throws UrbanspoonException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("insert into branch_images  values(?,?)");
			preparedStatement.setLong(1, branchId);
			preparedStatement.setString(2, fileName);
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

	public Branch insert(long restaurantId, Branch branch) throws UrbanspoonException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement(
					"insert into branch(location,city,state,country,postal_code,restaurant_id) values(?,?,?,?,?,?)");
			preparedStatement.setString(1, branch.getLocation());
			preparedStatement.setString(2, branch.getCity());
			preparedStatement.setString(3, branch.getState());
			preparedStatement.setString(4, branch.getCountry());
			preparedStatement.setInt(5, branch.getPostalCode());
			preparedStatement.setLong(6, restaurantId);
			if (preparedStatement.executeUpdate() > 0) {
				branch.setId(DAOUtility.getLatestId("branch"));
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(preparedStatement, connection);
		}
		return branch;
	}

	public List<Branch> getBranches(long restaurantId, boolean includeCuisines,boolean includeFeedbacks) throws UrbanspoonException {

		List<Branch> branchesList = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("select * from branch where restaurant_id = ?");
			preparedStatement.setLong(1, restaurantId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				branchesList = new ArrayList<Branch>();
				do {
					Branch branch = new Branch();
					branch.setId(resultSet.getInt(1));
					branch.setLocation(resultSet.getString(2));
					branch.setCity(resultSet.getString(3));
					branch.setState(resultSet.getString(4));
					branch.setCountry(resultSet.getString(5));
					branch.setPostalCode(resultSet.getInt(6));
					branch.setImagesList(getBranchImages(resultSet.getInt(1)));
					if (includeCuisines) {
						branch.setCuisinesList(new CuisineDAO().getCuisines(branch.getId(), true));
					}
					if (includeFeedbacks) {
						branch.setFeedbackList(new FeedbackDAO().getBranchFeedbacks(branch.getId()));
					}
					branchesList.add(branch);
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {
			DAOUtility.close(preparedStatement, connection);

		}
		return branchesList;
	}

	public Branch getBranch(int branchId, boolean includeCuisines) throws UrbanspoonException {
		Branch branch = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("select * from branch where id = ?");
			preparedStatement.setInt(1, branchId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				branch = new Branch();
				branch.setId(resultSet.getInt(1));
				branch.setLocation(resultSet.getString(2));
				branch.setCity(resultSet.getString(3));
				branch.setState(resultSet.getString(4));
				branch.setCountry(resultSet.getString(5));
				branch.setPostalCode(resultSet.getInt(6));
				branch.setImagesList(getBranchImages(resultSet.getInt(1)));
				if (includeCuisines) {
					branch.setCuisinesList(new CuisineDAO().getCuisines(branch.getId(), true));
				}

			}
		} catch (SQLException e) {
			throw new UrbanspoonException(e.toString());
		} finally {

			DAOUtility.close(preparedStatement, connection);

		}

		return branch;
	}

	public List<String> getBranchImages(int branchId) throws UrbanspoonException {
		List<String> imagesList = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DAOUtility.getConnection();
			preparedStatement = connection.prepareStatement("select image_name from branch_images where branch_id = ?");
			preparedStatement.setInt(1, branchId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				imagesList = new ArrayList<String>();
				do {
					imagesList.add(resultSet.getString(1));
				} while (resultSet.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtility.close(resultSet, preparedStatement, connection);
		}

		return imagesList;
	}

}
