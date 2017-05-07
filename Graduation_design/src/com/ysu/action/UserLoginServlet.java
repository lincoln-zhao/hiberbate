package com.ysu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysu.entity.User;
import com.ysu.service.UserService;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserService userService = new UserService();
		
		// 获取页面传递的用户名密码
		String UserName = "";
		String password = "";
		
		if (request.getParameter("userName") != null) {
			UserName = request.getParameter("userName");
		}
		if (request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		
		// 返回到页面的结果
		String returnStr = "success";
		
		// 调用service层，取得登录的用户
		User user = userService.userLogin(UserName, password);

		if (user == null) {
			System.out.println("用户不存在！");
			returnStr = "failed";
		} else {
			// 将查询到的结果放入session中
			request.getSession().setAttribute("loginUser", user);
		}

		// 结果返回页面
		response.getWriter().write(returnStr);
	}

}
