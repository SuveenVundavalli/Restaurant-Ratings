package com.ts.us.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ts.us.dao.BranchImageDAO;
import com.ts.us.model.BranchImage;

@Repository("branchImageDAO")
@Transactional
public class BranchImageDAOImpl implements BranchImageDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public BranchImageDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean save(BranchImage branchImage) {
		try {
			getCurrentSession().save(branchImage);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(BranchImage branchImage) {
		try {
			getCurrentSession().update(branchImage);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(int branchId) {
		try {
			getCurrentSession().createQuery("delete from Branch where branchId = :branchId").setInteger("branchId", branchId).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<BranchImage> getBranchImages(int branchId) {
		return getCurrentSession().createCriteria(BranchImage.class).add(Restrictions.eq("branchId", branchId)).list();
	}

}
