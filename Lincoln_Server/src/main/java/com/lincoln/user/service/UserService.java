package com.lincoln.user.service;

import java.util.List;

import com.lincoln.user.entity.User;

public interface UserService {
	
	public List<User> getAllUsers ();
	
	public User getUserById (String id);
	
	public int addUser (User user);
	
	public int deleteUserById (String id);
	
	public int updateUserById (User user);
}
