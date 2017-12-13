package com.ts.us.dao;

import com.ts.us.dto.User;
import com.ts.us.exception.UrbanspoonException;

public interface IUserDAO {
	public User insert(User user) throws UrbanspoonException;
	public User getUser(String email) throws UrbanspoonException;
	public User getUser(int userId) throws UrbanspoonException;
}
