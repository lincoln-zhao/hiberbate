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
}
