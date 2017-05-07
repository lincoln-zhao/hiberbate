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
		
		// ��ȡҳ�洫�ݵ��û�������
		String UserName = "";
		String password = "";
		
		if (request.getParameter("userName") != null) {
			UserName = request.getParameter("userName");
		}
		if (request.getParameter("password") != null) {
			password = request.getParameter("password");
		}
		
		// ���ص�ҳ��Ľ��
		String returnStr = "success";
		
		// ����service�㣬ȡ�õ�¼���û�
		User user = userService.userLogin(UserName, password);

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

}
