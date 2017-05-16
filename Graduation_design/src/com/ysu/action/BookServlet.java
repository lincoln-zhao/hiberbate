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
import com.ysu.service.BookService;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ����service����
	BookService bookService = new BookService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if ("returnBook".equals(request.getParameter("type"))) {
			// ��ʾ�û���Ϣҳ��
			returnBook(request, response);
		} else if ("allBorrowBook".equals(request.getParameter("type"))) {
			// ���ý��ȥ����
			getAllBorrowingBook(request, response);
		}
		
		
	}
	
	/**
	 * �û�����
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void returnBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡҳ�洫�ݵ�ͼ��ID
		String bookId = "";
		
		if (request.getParameter("bookId") != null) {
			bookId = request.getParameter("bookId");
		}
		
		// ��session��ȡ��user����
		User user = (User) request.getSession().getAttribute("loginUser");
		
		// ����service���������л������
		String result = bookService.returnBook(user.getUser_id(), bookId);
		
		// �жϻ���������
		if (!"success".equals(result)) {
			request.setAttribute("returnBookResult", result);
			request.getRequestDispatcher("/personal.jsp").forward(request, response);
		} else {
			request.setAttribute("returnBookResult", "����ɹ�");
			request.getRequestDispatcher("/user?type=showUser").forward(request, response);
		}
	}
	
	private void getAllBorrowingBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> bookList = bookService.getAllBorrowingBook();
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/manager2.jsp").forward(request, response);

	}

}
