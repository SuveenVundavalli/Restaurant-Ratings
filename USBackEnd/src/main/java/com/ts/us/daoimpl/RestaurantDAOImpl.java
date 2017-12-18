package com.ts.us.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ts.us.dao.RestaurantDAO;
import com.ts.us.model.Restaurant;

@Repository("restaurantDAO")
@Transactional
public class RestaurantDAOImpl implements RestaurantDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public RestaurantDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean save(Restaurant restaurant) {
		try {
			getCurrentSession().save(restaurant);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Restaurant restaurant) {
		try {
			getCurrentSession().update(restaurant);
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

	public Restaurant get(int id) {
		return (Restaurant) getCurrentSession().createCriteria(Restaurant.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	public Restaurant get(String govtRegistrationId) {
		return (Restaurant) getCurrentSession().createCriteria(Restaurant.class).add(Restrictions.eq("govtRegistrationId", govtRegistrationId)).uniqueResult();
		}

	public List<Restaurant> list() {
		return getCurrentSession().createCriteria(Restaurant.class).list();
		}

}
