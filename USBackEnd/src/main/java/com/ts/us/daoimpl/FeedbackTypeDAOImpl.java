package com.ts.us.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ts.us.dao.FeedbackTypeDAO;
import com.ts.us.model.FeedbackType;

@Repository("feedbackTypeDAO")
@Transactional
public class FeedbackTypeDAOImpl implements FeedbackTypeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public FeedbackTypeDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean save(FeedbackType feedbackType) {
		try {
			getCurrentSession().save(feedbackType);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(FeedbackType feedbackType) {
		try {
			getCurrentSession().update(feedbackType);
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

	public FeedbackType get(int id) {
		return (FeedbackType) getCurrentSession().createCriteria(FeedbackType.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	public List<FeedbackType> list() {
		return getCurrentSession().createCriteria(FeedbackType.class).list();
		}

}
