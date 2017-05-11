package com.ysu.service;

import com.ysu.dao.BookDao;

public class BookService {
	private BookDao bookDao = new BookDao();
	public String returnBook (String userId, String bookId) {
		return bookDao.returnBook(userId, bookId);
	}
}
