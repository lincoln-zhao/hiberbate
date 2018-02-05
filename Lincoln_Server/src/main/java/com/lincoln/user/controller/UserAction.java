package com.lincoln.user.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lincoln.user.entity.User;
import com.lincoln.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserAction {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/getAllUsers",method=RequestMethod.POST)  
	public @ResponseBody List<User> getAllUsers () {
		
		List<User> allUsers = userService.getAllUsers();
//		JSONArray ja = JSONArray.fromObject(allUsers);
		return allUsers;
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)  
	public @ResponseBody String addUser (HttpServletRequest request) {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		int result = userService.addUser(user);
		
		if (result == 1) {
			return "true";
		}
		
		return "false";
	}
	
	@RequestMapping("/getOneUser")
//	@RequestMapping(params="method=getAllUsers")
	public String getUserById (HttpServletRequest request) {
		
		String id = (String) request.getParameter("id");
		User user = userService.getUserById(id);
		String result = "id:" + user.getId() + ", name:" + user.getName() + ", password:" + user.getPassword();
		request.setAttribute("user", result);
		
		return "/userList.jsp";
	}
	
}
