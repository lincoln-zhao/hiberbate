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
		} else if ("allBooks".equals(request.getParameter("type"))) {
			// ȡ����������Ϣ
			getAllBooks(request, response);
		} else if ("addBook".equals(request.getParameter("type"))) {
			// ���ͼ��
			addBook(request, response);
		} else if ("modifyBook".equals(request.getParameter("type"))) {
			// �޸�ͼ��
			modifyBook(request, response);
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
	
	/**
	 * ȡ�õ�ǰ���н��ͼ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getAllBorrowingBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> bookList = bookService.getAllBorrowingBook();
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/manager2.jsp").forward(request, response);

	}
	
	/**
	 * ȡ������ͼ����Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getAllBooks (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> bookList = bookService.getAllBooks();
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/manager3.jsp").forward(request, response);
	}
	
	/**
	 * ���ͼ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡҳ�洫�ݵĲ���
		String bookId = "";
		String bookName = "";
		String author = "";
		String classification = "";
		String position = "";
		
		if (request.getParameter("bookId") != null) {
			bookId = request.getParameter("bookId");
		}
		if (request.getParameter("bookName") != null) {
			bookName = request.getParameter("bookName");
		}
		if (request.getParameter("author") != null) {
			author = request.getParameter("author");
		}
		if (request.getParameter("classification") != null) {
			classification = request.getParameter("classification");
		}
		if (request.getParameter("position") != null) {
			position = request.getParameter("position");
		}
		
		Book book = new Book();
		book.setBook_id(bookId);
		book.setBook_name(bookName);
		book.setAuthor(author);
		book.setClassification(classification);
		book.setPosition(position);
		
		boolean result = bookService.addBook(book);
		
		response.setCharacterEncoding("UTF-8");
		
		if (result) {
			response.getWriter().write("success");
		} else {
			response.getWriter().write("ͼ�����ʧ�ܣ������������ݣ�");
		}
	}
	
	/**
	 * �޸�ͼ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modifyBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡҳ�洫�ݵĲ���
		String bookId = "";
		String bookName = "";
		String author = "";
		String classification = "";
		String position = "";
		
		if (request.getParameter("bookId") != null) {
			bookId = request.getParameter("bookId");
		}
		if (request.getParameter("bookName") != null) {
			bookName = request.getParameter("bookName");
		}
		if (request.getParameter("author") != null) {
			author = request.getParameter("author");
		}
		if (request.getParameter("classification") != null) {
			classification = request.getParameter("classification");
		}
		if (request.getParameter("position") != null) {
			position = request.getParameter("position");
		}
		
		Book book = new Book();
		book.setBook_id(bookId);
		book.setBook_name(bookName);
		book.setAuthor(author);
		book.setClassification(classification);
		book.setPosition(position);
		
		
	}

}
