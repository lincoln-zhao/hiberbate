package com.ysu.service;

import java.util.List;

import com.ysu.dao.BookDao;
import com.ysu.entity.Book;
import com.ysu.entity.Classification;

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
	
	public Boolean delBook (String bookId) {
		return bookDao.delBook(bookId);
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
	
	public String addClassification (String name) {
		return bookDao.addClassification(name);
	}
	
	public String delClassification (String id) {
		return bookDao.delClassification(id);
	}
	
	public List<Classification> getAllClassification () {
		return bookDao.getAllClassification();
	}
	
	public List<Classification> getAHotClassification () {
		return bookDao.getAHotClassification();
	}
}
