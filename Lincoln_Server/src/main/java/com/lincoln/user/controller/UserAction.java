package com.lincoln.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public @ResponseBody String getAllUsers () {
		
		List<User> allUsers = userService.getAllUsers();
//		JSONArray ja = JSONArray.fromObject(allUsers);
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("rt", 1);
			map.put("data", allUsers);
			result = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)  
	public @ResponseBody String addUser (HttpServletRequest request) {
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		try {
			if (name != null && !"".equals(name)) {
				name = URLDecoder.decode(request.getParameter("name"), "UTF-8");
			}
			
			if (password != null && !"".equals(password)) {
				password = URLDecoder.decode(request.getParameter("password"), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "false";
		}
		
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
