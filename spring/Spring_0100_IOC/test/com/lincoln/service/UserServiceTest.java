package com.lincoln.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lincoln.model.User;

public class UserServiceTest {

	@Test
	public void testAdd() {
		ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
		
		UserService userService = act.getBean("userService", UserService.class);
		
		User user = new User();
		
		userService.add(user);
	}

}
