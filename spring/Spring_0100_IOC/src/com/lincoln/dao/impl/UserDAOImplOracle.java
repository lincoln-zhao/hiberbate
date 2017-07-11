package com.lincoln.dao.impl;

import com.lincoln.dao.UserDAO;
import com.lincoln.model.User;

public class UserDAOImplOracle implements UserDAO {

	@Override
	public void save(User user) {
		System.out.println("Oracle:user is saved.");
		
	}

}
