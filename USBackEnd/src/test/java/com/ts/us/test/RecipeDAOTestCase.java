package com.ts.us.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ts.us.dao.RecipeDAO;
import com.ts.us.model.Recipe;

public class RecipeDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static RecipeDAO recipeDAO;
	
	@Autowired
	static Recipe recipe;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ts.us");
		context.refresh();
		recipeDAO = (RecipeDAO) context.getBean("recipeDAO");
		recipe = (Recipe) context.getBean("recipe");
	}
	
	@Test
	public void saveTestTestCase() {
		recipe.setName("Chicken Pakodi");
		recipe.setDescription("Chicken Pakodi made with vencobb chicken");
		recipe.setCuisineId(1);
		recipe.setIsVeg(false);
		boolean flag = recipeDAO.save(recipe);
		assertEquals("saveTestTestCase",true, flag);
	}
	

}
