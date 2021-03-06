package com.ysu.service;

import java.util.List;

import com.ysu.dao.UserDao;
import com.ysu.entity.Admin;
import com.ysu.entity.Book;
import com.ysu.entity.User;

public class UserService {
	private UserDao userDao = new UserDao();
	
	public User userLogin (String UserName, String password) {
		return userDao.userLogin(UserName, password);
	}
	
	public boolean userRegister (User user) {
		return userDao.userRegister(user);
	}
	
	public List<Book> borrowdBook (String UserId) {
		return userDao.borrowdBook(UserId);
	}
	
	public List<Book> nowBorrowBook (String UserId) {
		return userDao.nowBorrowBook(UserId);
	}
	
	public Admin adminLogin (String adminrName, String password) {
		return userDao.adminLogin(adminrName, password);
	}
	
	public List<User> getAllUsers () {
		return userDao.getAllUsers();
	}
	
	public boolean delUser (String userId) {
		return userDao.delUser(userId);
	}
	
	public Boolean modifyUser (User user) {
		return userDao.modifyUser(user);
	}

}
