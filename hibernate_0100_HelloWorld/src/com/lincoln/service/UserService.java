package com.lincoln.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lincoln.Dao.UserDao;
import com.lincoln.entity.User;
import com.lincoln.util.GetSessionFactory;

public class UserService {
	// 创建Dao层对象
	UserDao userDao = new UserDao();
	
	/**
	 * 取得全部user
	 * @return
	 */
	public List<User> getAllUser () {
		return userDao.getAllUser();
	}
	
	/**
	 * 添加user
	 * @return
	 */
	public boolean addUser (User user) {
		return userDao.addUser(user);
	}
	
	/**
	 * 根据userId删除user
	 * @param user
	 * @return
	 */
	public boolean deleteUser (User user) {
		return userDao.deleteUser(user);
	}
	
	/**
	 * 根据userId修改user
	 * @param user
	 * @return
	 */
	public boolean modifyUser (User user) {
		return userDao.modifyUser(user);
	}
}
