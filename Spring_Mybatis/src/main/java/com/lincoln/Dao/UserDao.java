package com.lincoln.Dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lincoln.entity.User;

@Component
public interface UserDao {
	public List<User> getAllUser ();
}
