package com.ysu.service;

import java.util.List;

import com.ysu.dao.BookDao;
import com.ysu.entity.Book;

public class BookService {
	private BookDao bookDao = new BookDao();
	
	public String returnBook (String userId, String bookId) {
		return bookDao.returnBook(userId, bookId);
	}
	
	public List<Book> getAllBorrowingBook () {
		return bookDao.getAllBorrowingBook();
	}
	
	public List<Book> getAllBooks () {
		return bookDao.getAllBooks();
	}
	
	public Boolean addBook (Book book) {
		return bookDao.addBook(book);
	}
	
	public Boolean modifyBook (Book book) {
		return bookDao.modifyBook(book);
	}
	
	public List<Book> hotBook () {
		return bookDao.hotBook();
	}
	
	public List<Book> newBook () {
		return bookDao.newBook();
	}
	
	public Book getSingleBook (String bookId) {
		return bookDao.getSingleBook(bookId);
	}
	
	public String borrowBook (String bookId, String userId) {
		return bookDao.borrowBook(bookId, userId);
	}
	
	public List<Book> searchBook (String str) {
		return bookDao.searchBook(str);
	}
}
