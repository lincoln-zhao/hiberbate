package com.lincoln.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lincoln.Dao.UserDao;
import com.lincoln.entity.User;

@Component
public class UserService {
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}
	
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void getAllUser () {
		List<User> userList = userDao.getAllUser();
		
		for (User user:userList) {
			System.out.println(user.getId() + "---" + user.getName());
		}
	}
}
