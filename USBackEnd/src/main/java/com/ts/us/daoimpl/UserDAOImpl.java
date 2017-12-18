package com.ts.us.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ts.us.dao.UserDAO;
import com.ts.us.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean save(User user) {
		try {
			getCurrentSession().save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(User user) {
		try {
			getCurrentSession().update(user);
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

	public User get(int id) {
		return (User) getCurrentSession().createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	public User get(String email) {
		return (User) getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
		}

	public List<User> list() {
		return getCurrentSession().createCriteria(User.class).list();
		}

}
