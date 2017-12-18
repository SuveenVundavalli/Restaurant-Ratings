package com.ts.us.daoimpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ts.us.dao.TestDAO;
import com.ts.us.model.Test01;

@Repository("testDAO")
@Transactional
public class TestDAOImpl implements TestDAO{

	@Autowired 
	private SessionFactory sessionFactory;
	
	
	
	public TestDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}


	public boolean save(Test01 test) {
		try {
			getCurrentSession().save(test);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

}
