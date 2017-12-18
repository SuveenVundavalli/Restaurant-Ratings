package com.ts.us.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ts.us.dao.ServeDAO;
import com.ts.us.model.Serve;

@Repository("serveDAO")
@Transactional
public class ServeDAOImpl implements ServeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public ServeDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean save(Serve serve) {
		try {
			getCurrentSession().save(serve);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(Serve serve) {
		try {
			getCurrentSession().update(serve);
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

	public Serve get(int id) {
		return (Serve) getCurrentSession().createCriteria(Serve.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	public List<Serve> getServeByBranch(int branchId) {
		return getCurrentSession().createCriteria(Serve.class).add(Restrictions.eq("branchId", branchId)).list();
		}

	public List<Serve> list() {
		return getCurrentSession().createCriteria(Serve.class).list();
		}

}
