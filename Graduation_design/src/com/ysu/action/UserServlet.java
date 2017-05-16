package com.ysu.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysu.entity.Admin;
import com.ysu.entity.Book;
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
			// 用户注册
			register(request, response);
		} else if ("showUser".equals(request.getParameter("type"))) {
			// 显示用户信息页面
			showUser(request, response);
		} else if ("adminLogin".equals(request.getParameter("type"))) {
			// 管理员登录
			adminLogin(request, response);
		} else if ("allUser".equals(request.getParameter("type"))) {
			// 取得所有用户
			getAllUsers(request, response);
		} else if ("delUser".equals(request.getParameter("type"))) {
			// 删除用户
			delUser(request, response);
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
		String userName = "";
		String password = "";
		
		if (request.getParameter("userName") != null) {
			userName = request.getParameter("userName");
		}
		if (request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		
		// 返回到页面的结果
		String returnStr = "success";
		
		// 调用service层，取得登录的用户
		User user = userService.userLogin(userName, password);

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
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void register (HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 创建service层对象
		UserService userService = new UserService();
		
		// 获取页面传递的用户名密码
		String userName = "";
		String password = "";
		String sex = "";
		String phone = "";
		
		if (request.getParameter("userName") != null) {
			userName = request.getParameter("userName");
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
		user.setUser_name(userName);
		user.setPassword(password);
		user.setSex(sex);
		user.setPhone(phone);
		
		// 调用service层，注册用户
		boolean result = userService.userRegister(user);
		
		if (result) {
			// 如果注册成功，则登录
			userLogin(request, response);
		} else {
			System.out.println("用户名已注册！");
			// 错误信息返回页面
//			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
//			request.setCharacterEncoding("UTF-8");
			response.getWriter().write("用户名已注册！");
		}
	}
	
	/**
	 * 显示用户管理界面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建service层对象
		UserService userService = new UserService();
		
		// 从session中取得user对象
		User user = (User) request.getSession().getAttribute("loginUser");
		
		if (user != null) {
			// 调用service层，取得历史借阅数据
			List<Book> bookList = userService.borrowdBook(user.getUser_id());
			
			// 将数据放入request中
			request.setAttribute("bookList", bookList);
			
			// 调用service层，取得当前借阅
			List<Book> nowBookList = userService.nowBorrowBook(user.getUser_id());
			
			// 将数据放入request中
			request.setAttribute("nowBookList", nowBookList);
		}
		
		request.getRequestDispatcher("/personal.jsp").forward(request, response);
	}

	/**
	 * 管理员登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void adminLogin (HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 创建service层对象
		UserService userService = new UserService();
		
		// 获取页面传递的用户名密码
		String adminName = "";
		String password = "";

		
		if (request.getParameter("adminName") != null) {
			adminName = request.getParameter("adminName");
		}
		
		if (request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		
		// 调用service层，取得管理员对象
		Admin admin = userService.adminLogin(adminName, password);
		
		// 返回结果
		String returnStr = "success";
		
		if (admin == null) {
			System.out.println("用户不存在！");
			returnStr = "failed";
		} else {
			// 将查询到的结果放入session中
			request.getSession().setAttribute("adminUser", admin);
		}

		// 结果返回页面
		response.getWriter().write(returnStr);
	}
	
	/**
	 * 取得所有用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getAllUsers (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建service层对象
		UserService userService = new UserService();
		
		// 将数据放入request中
		request.setAttribute("nowBookList", userService.getAllUsers());
		request.getRequestDispatcher("/manager1.jsp").forward(request, response);
	}
	
	private void delUser (HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 创建service层对象
		UserService userService = new UserService();
		
		// 取得用户ID
		String userId = "";
		
		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
		}
		
		// 调用service对象方法
		boolean result = userService.delUser(userId);
		
		if (result) {
			response.getWriter().write("success");
		} else {
			System.out.println("用户删除失败！");
			// 错误信息返回页面
//			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
//			request.setCharacterEncoding("UTF-8");
			response.getWriter().write("用户删除失败！");
		}
		
		
		
	}
}
