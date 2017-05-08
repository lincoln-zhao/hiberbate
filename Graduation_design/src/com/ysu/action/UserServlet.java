package com.ysu.action;

import java.io.IOException;
import java.io.PrintWriter;

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
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		
		if ("login".equals(request.getParameter("type"))) {
			// 用户登录
			userLogin(request, response);
		} else if ("register".equals(request.getParameter("type"))) {
			
		}
		
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void userLogin (HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 创建service层对象
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
	
	private void register (HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 创建service层对象
		UserService userService = new UserService();
		
		// 获取页面传递的用户名密码
		String UserName = "";
		String password = "";
		String sex = "";
		String phone = "";
		
		if (request.getParameter("userName") != null) {
			UserName = request.getParameter("userName");
		}
		
		if (request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		
		if (request.getParameter("sex") != null) {
			sex = request.getParameter("sex");
		}
		
		if (request.getParameter("phone") != null) {
			phone = request.getParameter("phone");
		}
		
		User user = new User();
		user.setUser_name(UserName);
		user.setPassword(password);
		user.setSex(sex);
		user.setPhone(phone);
		
		// 调用service层，注册用户
		boolean result = userService.userRegister(user);
		
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        String name = "hello, servlet";  
        out.println("<html>");  
        out.println("<body>");  
        /** 
         * 超链接 
         */  
        out.println("<a href='ServletB?name="+name+"'>this is a test</a>");  
        /** 
         * 表单 
         */  
        out.println("<form action='ServletB' method='post'>");          
        out.println("用户名：");  
        out.println("<input type='text' name='username'>");  
        out.println("<input type='submit' vlue='提交'>");  
        out.println("</form>");  
        out.println("</body>");  
        out.println("</html>");  
        out.flush();  
	}

}
