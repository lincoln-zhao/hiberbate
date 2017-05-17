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
	// 创建service对象
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
			// 显示用户信息页面
			returnBook(request, response);
		} else if ("allBorrowBook".equals(request.getParameter("type"))) {
			// 所用借出去的书
			getAllBorrowingBook(request, response);
		} else if ("allBooks".equals(request.getParameter("type"))) {
			// 取得所有书信息
			getAllBooks(request, response);
		} else if ("addBook".equals(request.getParameter("type"))) {
			// 添加图书
			addBook(request, response);
		} else if ("modifyBook".equals(request.getParameter("type"))) {
			// 修改图书
			modifyBook(request, response);
		}
		
		
	}
	
	/**
	 * 用户还书
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void returnBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取页面传递的图书ID
		String bookId = "";
		
		if (request.getParameter("bookId") != null) {
			bookId = request.getParameter("bookId");
		}
		
		// 从session中取得user对象
		User user = (User) request.getSession().getAttribute("loginUser");
		
		// 调用service方法，进行还书操作
		String result = bookService.returnBook(user.getUser_id(), bookId);
		
		// 判断还书操作结果
		if (!"success".equals(result)) {
			request.setAttribute("returnBookResult", result);
			request.getRequestDispatcher("/personal.jsp").forward(request, response);
		} else {
			request.setAttribute("returnBookResult", "还书成功");
			request.getRequestDispatcher("/user?type=showUser").forward(request, response);
		}
	}
	
	/**
	 * 取得当前所有借出图书
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
	 * 取得所有图书信息
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
	 * 添加图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取页面传递的参数
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
			response.getWriter().write("图书添加失败，请检查输入内容！");
		}
	}
	
	/**
	 * 修改图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modifyBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取页面传递的参数
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
