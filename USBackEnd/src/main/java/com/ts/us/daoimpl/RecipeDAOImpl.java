package com.ts.us.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ts.us.dao.RecipeDAO;
import com.ts.us.model.Recipe;

@Repository("recipeDAO")
@Transactional
public class RecipeDAOImpl implements RecipeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public RecipeDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean save(Recipe recipe) {
		try {
			getCurrentSession().save(recipe);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Recipe recipe) {
		try {
			getCurrentSession().update(recipe);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(int id) {
		try {
			getCurrentSession().delete(get(id));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Recipe get(int id) {
		return (Recipe) getCurrentSession().createCriteria(Recipe.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	public List<Recipe> getRecipesByCusineId(int cusineId) {
		return getCurrentSession().createCriteria(Recipe.class).add(Restrictions.eq("cusineId", cusineId)).list();
		}

	public List<Recipe> list() {
		return getCurrentSession().createCriteria(Recipe.class).list();
		}

}
