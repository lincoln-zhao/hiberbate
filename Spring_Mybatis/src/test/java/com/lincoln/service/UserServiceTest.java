package com.lincoln.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

	private UserService userService;
	
	
	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	@Test
	public void test() {
//		userService.getAllUser();
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		
		UserService service = (UserService)ctx.getBean("userService");
		service.getAllUser();
		
		ctx.destroy();
	}

}
