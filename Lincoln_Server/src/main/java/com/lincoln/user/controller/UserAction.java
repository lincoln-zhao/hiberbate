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
	private ObjectMapper mapper;

	public ObjectMapper getMapper() {
		return mapper;
	}

	@Resource
	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 查询全部
	 * @return
	 */
	@RequestMapping(value="/getAllUsers",method=RequestMethod.POST,produces = "application/json; charset=utf-8")  
	public @ResponseBody String getAllUsers () {
		
		List<User> allUsers = userService.getAllUsers();
//		JSONArray ja = JSONArray.fromObject(allUsers);
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
	
	/**
	 * 添加用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addUser",method=RequestMethod.POST,produces = "application/json; charset=utf-8")  
	public @ResponseBody String addUser (HttpServletRequest request) {
		
		// 返回的数据
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String result = "";
		
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		try {
			if (name != null && !"".equals(name)) {
				name = URLDecoder.decode(name, "UTF-8");
			}
			
			if (password != null && !"".equals(password)) {
				password = URLDecoder.decode(password, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return result;
		}
		
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		int resultInt = userService.addUser(user);
		
		// 返回1代表添加成功
		if (resultInt == 1) {
			resultMap.put("rt", 1);
		} else {
			resultMap.put("rt", 0);
		}
		
		try {
			result = mapper.writeValueAsString(resultMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST,produces = "application/json; charset=utf-8")  
	public @ResponseBody String deleteUserById (HttpServletRequest request) {
		// 返回的数据
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String result = "";
		
		String id = request.getParameter("id");
		
		try {
			if (id != null && !"".equals(id)) {
				id = URLDecoder.decode(id, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return result;
		}
		
		int resultInt = userService.deleteUserById(id);
		
		// 返回1代表添加成功
		if (resultInt == 1) {
			resultMap.put("rt", 1);
		} else {
			resultMap.put("rt", 0);
		}
		
		try {
			result = mapper.writeValueAsString(resultMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 更改用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateUser",method=RequestMethod.POST,produces = "application/json; charset=utf-8")  
	public @ResponseBody String updateUserById (HttpServletRequest request) {
		// 返回的数据
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String result = "";
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		try {
			if (id != null && !"".equals(id)) {
				id = URLDecoder.decode(id, "UTF-8");
			}
			
			if (name != null && !"".equals(name)) {
				name = URLDecoder.decode(name, "UTF-8");
			}
			
			if (password != null && !"".equals(password)) {
				password = URLDecoder.decode(password, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return result;
		}
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setPassword(password);
		
		int resultInt = userService.updateUserById(user);
		
		// 返回1代表添加成功
		if (resultInt == 1) {
			resultMap.put("rt", 1);
		} else {
			resultMap.put("rt", 0);
		}
		
		try {
			result = mapper.writeValueAsString(resultMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
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
