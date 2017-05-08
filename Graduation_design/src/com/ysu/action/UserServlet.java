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
			// �û���¼
			userLogin(request, response);
		} else if ("register".equals(request.getParameter("type"))) {
			
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
	
	private void register (HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ����service�����
		UserService userService = new UserService();
		
		// ��ȡҳ�洫�ݵ��û�������
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
		
		// ����service�㣬ע���û�
		boolean result = userService.userRegister(user);
		
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        String name = "hello, servlet";  
        out.println("<html>");  
        out.println("<body>");  
        /** 
         * ������ 
         */  
        out.println("<a href='ServletB?name="+name+"'>this is a test</a>");  
        /** 
         * �� 
         */  
        out.println("<form action='ServletB' method='post'>");          
        out.println("�û�����");  
        out.println("<input type='text' name='username'>");  
        out.println("<input type='submit' vlue='�ύ'>");  
        out.println("</form>");  
        out.println("</body>");  
        out.println("</html>");  
        out.flush();  
	}

}
