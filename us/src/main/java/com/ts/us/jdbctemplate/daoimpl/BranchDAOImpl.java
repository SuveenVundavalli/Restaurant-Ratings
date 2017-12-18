package com.ts.us.jdbctemplate.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ts.us.dao.IBranchDAO;
import com.ts.us.dto.Branch;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.rowmapper.BranchRowMapper;

//@Component
public class BranchDAOImpl implements IBranchDAO{

	//JdbcTemplate jdbcTemplate = new JdbcTemplate();
	@Autowired 
	@Qualifier("jdbcTemplate1")
	JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean addImage(long branchId, String fileName) throws UrbanspoonException {
		System.out.println("Starting of addImage in BranchDAOImpl, --> jdbcTemplate"+jdbcTemplate);
		String query = "insert into branch_images  values(?,?)";
		int rowsUpdated = jdbcTemplate.update(query, new Object[] {branchId, fileName});
		if(rowsUpdated>0)
			return true;
		return false;
	}

	@Override
	public Branch insert(long restaurantId, Branch branch) throws UrbanspoonException {
		System.out.println("Starting of insert in BranchDAOImpl, --> jdbcTemplate"+jdbcTemplate);
		int rowsUpdated = jdbcTemplate.update("insert into branch(location,city,state,country,postal_code,restaurant_id) values(?,?,?,?,?,?)", new Object[] {branch.getLocation(), branch.getCity(), branch.getState(), branch.getCountry(), branch.getPostalCode(), restaurantId});
		if(rowsUpdated > 0) {
			int id = jdbcTemplate.queryForObject("select max(id) from branch", Integer.class);
			branch.setId(id);
		}
		return branch;
	}

	@Override
	public List<Branch> getBranches(long restaurantId, boolean includeCuisines, boolean includeFeedbacks)
			throws UrbanspoonException {
		System.out.println("Starting of getBranches in BranchDAOImpl, --> jdbcTemplate : "+jdbcTemplate);
		BranchRowMapper.includeCuisines = includeCuisines;
		BranchRowMapper.includeFeedbacks = includeFeedbacks;
		System.out.println(jdbcTemplate);
		return jdbcTemplate.query("select * from branch where restaurant_id = ?", new Object[] {restaurantId}, new BranchRowMapper());
	}

	@Override
	public Branch getBranch(int branchId, boolean includeCuisines) throws UrbanspoonException {
		System.out.println("Starting of getBranch in BranchDAOImpl, --> jdbcTemplate"+jdbcTemplate);
		BranchRowMapper.includeCuisines = includeCuisines;
		BranchRowMapper.includeFeedbacks = false;
		return jdbcTemplate.queryForObject("select * from branch where id = ?", new Object[] {branchId}, new BranchRowMapper());
	}

	@Override
	public List<String> getBranchImages(int branchId) throws UrbanspoonException {
		System.out.println("Starting of getBranchImages in BranchDAOImpl, --> jdbcTemplate"+jdbcTemplate);
		return jdbcTemplate.query("select image_name from branch_images where branch_id = ?", new Object[] {branchId}, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new String(rs.getString(1));
			}});
	}

}
