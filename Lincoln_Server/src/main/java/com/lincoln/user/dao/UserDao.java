package com.lincoln.user.dao;

import java.util.List;

import com.lincoln.user.entity.User;


public interface UserDao {
	
	public List<User> getAllUsers ();
	
	public User getUserById (String id);
	
	public int addUser (User user);
}
