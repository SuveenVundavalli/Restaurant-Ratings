package com.ts.us.jdbctemplate.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.RowSetWarning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ts.us.dao.IBranchDAO;
import com.ts.us.dao.IRestaurantDAO;
import com.ts.us.dto.Restaurant;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.rowmapper.RestaurantRowMapper;

@Component
public class RestaurantDAOImpl implements IRestaurantDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	IBranchDAO branchDAO; 

	@Override
	public List<Restaurant> getRestaurants(boolean includeBranches) throws UrbanspoonException {
		RestaurantRowMapper.includeBranches = includeBranches;
		return jdbcTemplate.query("select * from restaurant", new RestaurantRowMapper());  
	}

	@Override
	public Restaurant getRestaurant(int restaurantId, boolean includeBranches) throws UrbanspoonException {
		RestaurantRowMapper.includeBranches = includeBranches;
		String query = "select * from restaurant where id = ?";
		Restaurant restaurant = (Restaurant) jdbcTemplate.queryForObject(query,new Object[] {restaurantId}, new RestaurantRowMapper());
		System.out.println("rest"+restaurant);
		return restaurant;
	}

	@Override
	public Restaurant insert(Restaurant restaurant) throws UrbanspoonException {
		int rowsUpdated = jdbcTemplate.update("insert into restaurant(govt_registration_id,name,password) values(?,?,?)", new Object[] {restaurant.getGovtRegistrationtId(), restaurant.getName(), restaurant.getPassword()});
		System.out.println("rowsUpdated : "+rowsUpdated);
		if(rowsUpdated>0) {
			int id = jdbcTemplate.queryForObject("select max(id) from restaurant", Integer.class);
			restaurant.setId(id);
		}
		return restaurant;
	}

	@Override
	public Restaurant getRestaurant(String govtRegistrationId, boolean includeBranches) throws UrbanspoonException {
		RestaurantRowMapper.includeBranches = includeBranches;
		String query = "select * from restaurant where govt_registration_id = ?";
		Restaurant restaurant = (Restaurant) jdbcTemplate.queryForObject(query,new Object[] {govtRegistrationId}, new RestaurantRowMapper());
		System.out.println("rest"+restaurant);
		return restaurant;
	}

	@Override
	public boolean updateLogoAddress(long restaurantId, String fileName) throws UrbanspoonException {
		String query = "update restaurant set logo_name = ? where id = ?";
		int rowsUpdated = jdbcTemplate.update(query, new Object[] {fileName,restaurantId});
		if(rowsUpdated > 0)
			return true;
		return false;
	}

}
