package com.ysu.service;

import com.ysu.dao.UserDao;
import com.ysu.entity.User;

public class UserService {
	private UserDao userDao = new UserDao();
	
	public User userLogin (String UserName, String password) {
		return userDao.userLogin(UserName, password);
	}
	
	public boolean userRegister (User user) {
		return userDao.userRegister(user);
	}
}
