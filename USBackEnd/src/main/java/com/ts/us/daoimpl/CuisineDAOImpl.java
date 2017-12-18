package com.ts.us.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ts.us.dao.CuisineDAO;
import com.ts.us.model.Cuisine;

@Repository("cuisineDAO")
@Transactional
public class CuisineDAOImpl implements CuisineDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public CuisineDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean save(Cuisine cuisine) {
		try {
			getCurrentSession().save(cuisine);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Cuisine cuisine) {
		try {
			getCurrentSession().update(cuisine);
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

	public Cuisine get(int id) {
		return (Cuisine) getCurrentSession().createCriteria(Cuisine.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	public List<Cuisine> list() {

		return getCurrentSession().createCriteria(Cuisine.class).list();
	
	}

}
