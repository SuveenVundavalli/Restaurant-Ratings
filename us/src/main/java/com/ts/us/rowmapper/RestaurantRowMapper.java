package com.ts.us.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.ts.us.dao.IBranchDAO;
import com.ts.us.daoimpl.BranchDAO;
import com.ts.us.dto.Restaurant;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.jdbctemplate.daoimpl.BranchDAOImpl;

public class RestaurantRowMapper implements RowMapper<Restaurant>{

	public static boolean includeBranches;	
	
	@Autowired 
	IBranchDAO branchDAO;

	@Override
	public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(rs.getInt(1));
        restaurant.setGovtRegistrationtId(rs.getString(2));
        restaurant.setName(rs.getString(3));
        restaurant.setPassword(rs.getString(4));
        restaurant.setLogo(rs.getString(5));        
        if(includeBranches) {
        	try {
        		System.out.println("BranchDAO : "+branchDAO);
        		System.out.println("Restaurant : "+restaurant);
				restaurant.setBranchesList(new BranchDAO().getBranches(restaurant.getId(), true, true));
			} catch (UrbanspoonException e) {
				e.printStackTrace();
			}
        }
		return restaurant;
	}

}
