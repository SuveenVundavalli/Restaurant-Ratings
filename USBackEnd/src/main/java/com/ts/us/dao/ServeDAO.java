package com.ts.us.dao;

import java.util.List;

import com.ts.us.model.Serve;

public interface ServeDAO {
	boolean save(Serve serve);	
	boolean update(Serve serve);	
	boolean delete(int id);
	Serve get(int id);
	List<Serve> getServeByBranch(int branchId);
	List<Serve> list();
	
	
	
}
