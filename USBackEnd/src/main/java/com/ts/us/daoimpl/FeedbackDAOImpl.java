package com.ts.us.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ts.us.dao.FeedbackDAO;
import com.ts.us.model.Feedback;

@Repository("feedbackDAO")
@Transactional
public class FeedbackDAOImpl implements FeedbackDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public FeedbackDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean save(Feedback feedback) {
		try {
			getCurrentSession().save(feedback);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Feedback feedback) {
		try {
			getCurrentSession().update(feedback);
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

	public Feedback get(int id) {
		return (Feedback) getCurrentSession().createCriteria(Feedback.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	public List<Feedback> getBranchFeedbacks(int branchId) {
		return getCurrentSession().createCriteria(Feedback.class).add(Restrictions.eq("branchId", branchId)).list();
		}

	public List<Feedback> getRecipeFeedbacks(int recipeId) {
		return getCurrentSession().createCriteria(Feedback.class).add(Restrictions.eq("recipeId", recipeId)).list();
		}

	public List<Feedback> list() {
		return getCurrentSession().createCriteria(Feedback.class).list();
		}

}
