package com.ts.us.jdbctemplate.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ts.us.dao.IRecipeDAO;
import com.ts.us.daoimpl.FeedbackDAO;
import com.ts.us.dto.Recipe;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.rowmapper.RecipeRowMapper;

@Component
public class RecipeDAOImpl implements IRecipeDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Recipe insert(int cuisineId, Recipe recipe) throws UrbanspoonException {
		System.out.println("Jdbctemplate : "+jdbcTemplate);
		String querys = "insert into recipe(name,description,cuisine_id, is_veg) values(?,?,?,?)";
		int rowsUpdated = jdbcTemplate.update(querys, new Object[] {recipe.getName(), recipe.getDescription(),cuisineId, recipe.isVeg()});
		if(rowsUpdated > 0) {
			String maxQuery = "select max(id) from recipe";
			recipe.setId(jdbcTemplate.queryForObject(maxQuery, Integer.class));
		}
		return recipe;
	}

	@Override
	public List<Recipe> getRecipes() throws UrbanspoonException {
		System.out.println("Jdbctemplate : "+jdbcTemplate);
		String querys = "select * from recipe";
		return jdbcTemplate.query(querys, new RecipeRowMapper());
	}

	@Override
	public List<Recipe> getRecipes(int cuisineId) throws UrbanspoonException {
		System.out.println("Jdbctemplate : "+jdbcTemplate);
		String querys = "select r.* from recipe r, cuisine c where r.cuisine_id = c.id and c.id = ?";
		return jdbcTemplate.query(querys, new Object[] {cuisineId} ,new RecipeRowMapper());
	
	}

	@Override
	public Recipe getRecipe(int recipeId) throws UrbanspoonException {
		System.out.println("Jdbctemplate : "+jdbcTemplate);
		String querys = "select * from recipe where id = ?";
		return jdbcTemplate.queryForObject(querys, new Object[] {recipeId},new RecipeRowMapper());
	}

	@Override
	public List<Recipe> getRecipes(int cuisineId, int branchId) throws UrbanspoonException {
		System.out.println("Jdbctemplate : "+jdbcTemplate);
		String querys = "select r.*,price,image_name from recipe r, cuisine c, serve s where r.cuisine_id = c.id  and r.id = s.recipe_id and c.id = ? and s.branch_id = ?";
		return jdbcTemplate.query(querys, new Object[] {cuisineId, branchId}, new RowMapper<Recipe>() {

			@Override
			public Recipe mapRow(ResultSet resultSet, int rowCount) throws SQLException {
				Recipe recipe = new Recipe();
				recipe.setId(resultSet.getInt(1));
				recipe.setName(resultSet.getString(2));
				recipe.setDescription(resultSet.getString(3));
				recipe.setVeg(resultSet.getBoolean(5));
				recipe.setPrice(resultSet.getFloat(6));
				recipe.setImage(resultSet.getString(7));
				try {
					recipe.setFeedbackList(new FeedbackDAO().getRecipeFeedbacks(recipe.getId(),branchId));
				} catch (UrbanspoonException e) {
					e.printStackTrace();
				}
				return recipe;
			}
			
		});
	}

	@Override
	public boolean addRecipeToBranch(long recipeId, long branchId, float price, String imagePath)
			throws UrbanspoonException {
		System.out.println("Jdbctemplate : "+jdbcTemplate);
		String querys = "insert into serve values(?,?,?,?)";
		int x = jdbcTemplate.update(querys, new Object[] {branchId, recipeId, price, imagePath});
		if(x > 0)
			return true;
		return false;
	}

}
