package com.ts.us.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.ts.us.dao.ICuisineDAO;
import com.ts.us.daoimpl.CuisineDAO;
import com.ts.us.daoimpl.FeedbackDAO;
import com.ts.us.dto.Branch;

public class BranchRowMapper implements RowMapper<Branch> {
	public static boolean includeCuisines, includeFeedbacks;


	@Override
	public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
		Branch branch = new Branch();
		branch.setId(rs.getInt(1));
		branch.setLocation(rs.getString(2));
		branch.setCity(rs.getString(3));
		branch.setState(rs.getString(4));
		branch.setCountry(rs.getString(5));
		branch.setPostalCode(rs.getInt(6));
		try {
			if (includeCuisines)
				branch.setCuisinesList(new CuisineDAO().getCuisines(branch.getId(), true));
			if (includeFeedbacks)
				branch.setFeedbackList(new FeedbackDAO().getBranchFeedbacks(branch.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return branch;
	}

}
