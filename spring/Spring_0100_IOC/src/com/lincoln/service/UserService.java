package com.lincoln.service;

import com.lincoln.dao.UserDAO;
import com.lincoln.model.User;

public class UserService {
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void add (User user) {
		userDAO.save(user);
	}
}
