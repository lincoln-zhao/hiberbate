package com.ysu.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysu.entity.Book;
import com.ysu.entity.Classification;
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
			// ����
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
		} else if ("delBook".equals(request.getParameter("type"))) {
			// ɾ��ͼ��
			delBook(request, response);
		} else if ("getSingleBook".equals(request.getParameter("type"))) {
			// ��ȡ����ͼ����Ϣ
			getSingleBook(request, response);
		} else if ("borrowBook".equals(request.getParameter("type"))) {
			// ����
			borrowBook(request, response);
		} else if ("search".equals(request.getParameter("type"))) {
			// ͼ���ѯ
			searchBook(request, response);
		} else if ("addClassification".equals(request.getParameter("type"))) {
			// ��ӷ���
			addClassification(request, response);
		} else if ("delClassification".equals(request.getParameter("type"))) {
			// ɾ������
			delClassification(request, response);
		} else if ("getAllClassification".equals(request.getParameter("type"))) {
			// ȡ��ȫ������
			getAllClassification(request, response);
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
		// �û���������Ա����flg
		boolean isAdmin = false;
		// ��ȡҳ�洫�ݵ�ͼ��ID
		String bookId = "";
		String userId = "";
		
		if (request.getParameter("bookId") != null) {
			bookId = request.getParameter("bookId");
		}
		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
			isAdmin = true;
		} else {
			// ��session��ȡ��user����
			User user = (User) request.getSession().getAttribute("loginUser");
			userId = user.getUser_id();
		}
		

		
		// ����service���������л������
		String result = bookService.returnBook(userId, bookId);
		
		// �жϻ���������
		if (!"success".equals(result)) {
			request.setAttribute("returnBookResult", result);
		} else {
			request.setAttribute("returnBookResult", "����ɹ�");
		}
		
		if (isAdmin) {
			request.getRequestDispatcher("/book?type=allBorrowBook").forward(request, response);
		} else {
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
		List<Classification> classificationList = bookService.getAllClassification();
		request.setAttribute("bookList", bookList);
		request.setAttribute("classificationList", classificationList);
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
		String picture = "";
		
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
		if (request.getParameter("picture") != null) {
			picture = request.getParameter("picture");
		}
		
		Book book = new Book();
		book.setBook_id(bookId);
		book.setBook_name(bookName);
		book.setAuthor(author);
		book.setClassification(classification);
		book.setPosition(position);
		book.setCoverPicture(picture);
		
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
		
		boolean result = bookService.modifyBook(book);
		
		response.setCharacterEncoding("UTF-8");
		
		if (result) {
			response.getWriter().write("success");
		} else {
			response.getWriter().write("ͼ���޸�ʧ�ܣ������������ݣ�");
		}
	}
	
	/**
	 * ɾ��ͼ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private  void delBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡҳ�洫�ݵĲ���
		String bookId = "";
		
		if (request.getParameter("bookId") != null) {
			bookId = request.getParameter("bookId");
		}
		
		Boolean result = bookService.delBook(bookId);
		
		if (result) {
			response.getWriter().write("success");
		} else {
			response.getWriter().write("ͼ��ɾ��ʧ�ܣ�");
		}
	}
	
	/**
	 * ��ѯ����ͼ����Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getSingleBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡҳ�洫�ݵĲ���
		String bookId = "";
		
		if (request.getParameter("bookId") != null) {
			bookId = request.getParameter("bookId");
		}
		
		Book book = bookService.getSingleBook(bookId);
		
		request.setAttribute("book", book);
		request.getRequestDispatcher("/booksdetails.jsp").forward(request, response);
	}
	
	/**
	 * ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void borrowBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡҳ�洫�ݵĲ���
		String bookId = "";
		String userId = "";
		
		if (request.getParameter("bookId") != null) {
			bookId = request.getParameter("bookId");
		}
		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
		}
		
		String result = bookService.borrowBook(bookId, userId);
		
		response.getWriter().write(result);
	}
	
	/**
	 * ͼ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void searchBook (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡҳ�洫�ݵĲ���
		String searchStr = "";
		
		if (request.getParameter("search") != null) {
			searchStr = request.getParameter("search");
		}
		
		searchStr = new String(searchStr.getBytes("ISO-8859-1"), "UTF-8");
		
		List<Book> bookList = bookService.searchBook(searchStr);
		
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/bookslist.jsp").forward(request, response);
	}
	
	/**
	 * ��ӷ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addClassification (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡҳ�洫�ݵĲ���
		String name = "";
		
		if (request.getParameter("c_name") != null) {
			name = request.getParameter("c_name");
		}
		
		
		
		String result = bookService.addClassification(name);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	}
	
	/**
	 * ɾ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delClassification (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡҳ�洫�ݵĲ���
		String id = "";
		
		if (request.getParameter("c_id") != null) {
			id = request.getParameter("c_id");
		}
		
		id = new String(id.getBytes("ISO-8859-1"), "UTF-8");
		
		String result = bookService.delClassification(id);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	}
	
	/**
	 * ��ȡȫ��������Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getAllClassification (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Classification> classificationList = bookService.getAllClassification();
		
		request.setAttribute("classificationList", classificationList);
		request.getRequestDispatcher("/manager4.jsp").forward(request, response);
	}
	
	
}
