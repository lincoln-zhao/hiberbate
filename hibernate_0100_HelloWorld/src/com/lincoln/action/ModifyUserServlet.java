package com.lincoln.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lincoln.entity.User;
import com.lincoln.service.UserService;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 从页面取得参数
		String user_id = "";
		if (request.getParameter("user_id") != null) {
			user_id = request.getParameter("user_id").toString();
		}
		
		String user_name = "";
		if (request.getParameter("user_name") != null) {
			user_name = request.getParameter("user_name").toString();
		}
		
		String password = "";
		if (request.getParameter("password") != null) {
			password = request.getParameter("password").toString();
		}
		
		// 创建对象
		User user = new User();
		user.setUser_id(user_id);
		user.setUser_name(user_name);
		user.setPassword(password);
		
		// 调用service层，取得数据
		UserService userService = new UserService();
		Boolean returnBoolean = userService.modifyUser(user);
		
		// 结果返回页面
		response.getWriter().write(returnBoolean.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
