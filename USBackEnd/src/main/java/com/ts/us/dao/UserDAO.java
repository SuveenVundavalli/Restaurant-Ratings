package com.ts.us.dao;

import java.util.List;

import com.ts.us.model.Restaurant;
import com.ts.us.model.User;

public interface UserDAO {
	boolean save(User user);	
	boolean update(User user);	
	boolean delete(int id);	
	User get(int id);
	User get(String email);
	List<User> list();
}
