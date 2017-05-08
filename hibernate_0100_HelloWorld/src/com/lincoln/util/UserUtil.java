package com.lincoln.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.lincoln.entity.User;

/**
 * 从request中取得user对象
 * @author lincoln
 *
 */
public class UserUtil {
	public static User getUserFromRequest (HttpServletRequest request) throws UnsupportedEncodingException {
		// 从页面取得参数
		String user_id = "";
		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id").toString();
			user_id = new String(user_id.getBytes("ISO-8859-1"),"UTF-8");
		}
		
		String user_name = "";
		if (request.getParameter("user_name") != null) {
			user_name = request.getParameter("user_name").toString();
			user_name = new String(user_name.getBytes("ISO-8859-1"),"UTF-8");
		}
		
		String password = "";
		if (request.getParameter("password") != null) {
			password = request.getParameter("password").toString();
			password = new String(password.getBytes("ISO-8859-1"),"UTF-8");
		}
		
		// 创建对象
		User user = new User();
		user.setUser_id(user_id);
		user.setUser_name(user_name);
		user.setPassword(password);
		
		return user;
	}
}
