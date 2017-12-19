package com.ts.us.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ts.us.dao.BranchDAO;
import com.ts.us.model.Branch;

@Repository("branchDAO")
@Transactional
public class BranchDAOImpl implements BranchDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public BranchDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean save(Branch branch) {
		try {
			getCurrentSession().save(branch);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Branch branch) {
		try {
			getCurrentSession().update(branch);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(int branchId) {

		try {
			getCurrentSession().delete(get(branchId));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public Branch get(int id) {
		return (Branch) getCurrentSession().createCriteria(Branch.class).add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	public List<Branch> getRestaurantBranches(int restaurantId) {
		return getCurrentSession().createCriteria(Branch.class).add(Restrictions.eq("restaurantId", restaurantId)).list();
	}

	public List<Branch> list() {
		return getCurrentSession().createCriteria(Branch.class).list();
	
	}

	public Branch get(String location, int restaurantId) {
		return (Branch) getCurrentSession().createCriteria(Branch.class).add(Restrictions.eq("location", location)).add(Restrictions.eq("restaurantId", restaurantId)).uniqueResult();
	}

}
