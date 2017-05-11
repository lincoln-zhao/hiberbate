package com.ysu.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			// �û���¼
			userLogin(request, response);
		} else if ("register".equals(request.getParameter("type"))) {
			// �û�ע��
			register(request, response);
		} else if ("showUser".equals(request.getParameter("type"))) {
			// ��ʾ�û���Ϣҳ��
			showUser(request, response);
		}
		
	}
	
	/**
	 * �û���¼
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void userLogin (HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ����service�����
		UserService userService = new UserService();
		
		// ��ȡҳ�洫�ݵ��û�������
		String userName = "";
		String password = "";
		
		if (request.getParameter("userName") != null) {
			userName = request.getParameter("userName");
		}
		if (request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		
		// ���ص�ҳ��Ľ��
		String returnStr = "success";
		
		// ����service�㣬ȡ�õ�¼���û�
		User user = userService.userLogin(userName, password);

		if (user == null) {
			System.out.println("�û������ڣ�");
			returnStr = "failed";
		} else {
			// ����ѯ���Ľ������session��
			request.getSession().setAttribute("loginUser", user);
		}

		// �������ҳ��
		response.getWriter().write(returnStr);
	}
	
	/**
	 * �û�ע��
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void register (HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ����service�����
		UserService userService = new UserService();
		
		// ��ȡҳ�洫�ݵ��û�������
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
		
		// ����service�㣬ע���û�
		boolean result = userService.userRegister(user);
		
		if (result) {
			// ���ע��ɹ������¼
			userLogin(request, response);
		} else {
			System.out.println("�û�����ע�ᣡ");
			// ������Ϣ����ҳ��
//			response.setHeader("content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
//			request.setCharacterEncoding("UTF-8");
			response.getWriter().write("�û�����ע�ᣡ");
		}
	}
	
	/**
	 * ��ʾ�û��������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ����service�����
		UserService userService = new UserService();
		
		// ��session��ȡ��user����
		User user = (User) request.getSession().getAttribute("loginUser");
		
		if (user != null) {
			// ����service�㣬ȡ����ʷ��������
			List<Book> bookList = userService.borrowdBook(user.getUser_id());
			
			// �����ݷ���request��
			request.setAttribute("bookList", bookList);
			
			// ����service�㣬ȡ�õ�ǰ����
			List<Book> nowBookList = userService.nowBorrowBook(user.getUser_id());
			
			// �����ݷ���request��
			request.setAttribute("nowBookList", nowBookList);
		}
		
		request.getRequestDispatcher("/personal.jsp").forward(request, response);
	}

}
