package com.ts.us.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ts.us.dto.Recipe;

public class RecipeRowMapper implements RowMapper<Recipe>{

	@Override
	public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
		Recipe recipe = new Recipe();
		recipe.setId(rs.getInt(1));
		recipe.setName(rs.getString(2));
		recipe.setDescription(rs.getString(3));
		recipe.setVeg(rs.getBoolean(4));
		return recipe;
	}

}
