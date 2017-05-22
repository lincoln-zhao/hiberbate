package com.ysu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ysu.entity.Book;
import com.ysu.util.DBUtil;

public class BookDao {
	/**
	 * �û�����
	 * @param userId
	 * @param bookId
	 * @return
	 */
	public String returnBook (String userId, String bookId) {
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false); // ���������Զ��ύ
			
			// ����û���������ҳ����ʾһ��
			String sqlSelect = " SELECT T_BORROWBOOK.BOOK_ID "
							 + "   FROM T_BORROWBOOK "
							 + "  WHERE T_BORROWBOOK.USER_ID = ?";
			
			ps = conn.prepareStatement(sqlSelect);
			ps.setInt(1, Integer.parseInt(userId));
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				if (!bookId.equals(rs.getString("BOOK_ID"))) {
					ps.close();
					ps = null;
					return "ϵͳ����ά�����������Ա��ϵ";
				}
			} else {
				ps.close();
				ps = null;
				return "ϵͳ����ά�����������Ա��ϵ";
			}
			
			ps.close();
			ps = null;
			
			// ȡ�ý���ʱ���뻹��ʱ��
			Date borrowDate = null;
			Date returnDate = null;
			String sqlSelectTime = " SELECT BORROW_OUT_TIME "
								 + "       ,SYSDATE() AS RETURN_TIME "
								 + "   FROM T_BORROWBOOK "
								 + "  WHERE BOOK_ID = ? "
								 + "    AND USER_ID = ? ";
			
			ps = conn.prepareStatement(sqlSelectTime);
			ps.setString(1, bookId);
			ps.setString(2, userId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				borrowDate = rs.getDate("BORROW_OUT_TIME");
				returnDate = rs.getDate("RETURN_TIME");
			}
			
			// ɾ�������
			String sqlDelUserBorrow = " DELETE FROM T_BORROWBOOK "
					 				+ "  WHERE BOOK_ID = ? "
					 				+ "    AND USER_ID = ? ";
			
			ps = conn.prepareStatement(sqlDelUserBorrow);
			ps.setString(1, bookId);
			ps.setString(2, userId);
			int lines = ps.executeUpdate();
			if (lines <= 0) {
				conn.rollback();
				return "ϵͳ����ά�����������Ա��ϵ";
			}
			
			// ����ͼ���
			String sqlUpadeBook = " UPDATE T_BOOK "
								+ "    SET NUMBER = NUMBER + 1 "
								+ "  WHERE BOOK_ID = ? ";
			
			ps = conn.prepareStatement(sqlUpadeBook);
			ps.setString(1, bookId);
			lines = ps.executeUpdate();
			if (lines <= 0) {
				conn.rollback();
				return "ϵͳ����ά�����������Ա��ϵ";
			}
			
			// �����û��������
			String sqlInsert = " INSERT INTO T_BORROWBOOKHISTORY (USER_ID, BOOK_ID, START_DATE, END_DATE) "
							 + "  VALUES (?, ?, ?, ?) ";
			
			ps = conn.prepareStatement(sqlInsert);
			ps.setInt(1, Integer.parseInt(userId));
			ps.setString(2, bookId);
			ps.setDate(3, borrowDate);
			ps.setDate(4, returnDate);
			lines = ps.executeUpdate();
			if (lines <= 0) {
				conn.rollback();
				return "ϵͳ����ά�����������Ա��ϵ";
			}
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ִ�����ݲ����쳣");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("ִ�����ݲ����쳣");
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�PreparedStatement��Connection�쳣");
			}
		}
		
		return "success";
	}
	
	/**
	 * ȡ�����е�ǰ���ȥ����
	 * @return
	 */
	public List<Book> getAllBorrowingBook () {
		List<Book> bookList = new ArrayList<Book>();
		
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			String sql = " SELECT T_USER.USER_NAME "
					   + "       ,T_USER.USER_ID "
					   + "       ,T_BOOK.BOOK_ID "
					   + "       ,T_BOOK.BOOK_NAME "
					   + "       ,T_BOOK.AUTHOR "
					   + "       ,T_BORROWBOOK.BORROW_OUT_TIME "
					   + "       ,DATE_ADD(T_BORROWBOOK.BORROW_OUT_TIME, INTERVAL 7 DAY) AS RETURN_TIME "
					   + "   FROM T_BORROWBOOK, T_BOOK, T_USER "
					   + "  WHERE T_BORROWBOOK.USER_ID = T_USER.USER_ID "
					   + "    AND T_BORROWBOOK.BOOK_ID = T_BOOK.BOOK_ID";
	
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getString("BOOK_ID"));
				book.setBook_name(rs.getString("BOOK_NAME"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setStart_date(rs.getDate("BORROW_OUT_TIME"));
				book.setEnd_date(rs.getDate("RETURN_TIME"));
				book.setUser_name(rs.getString("USER_NAME"));
				book.setUser_id(rs.getString("USER_ID"));
				
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ִ�����ݲ����쳣");
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�PreparedStatement��Connection�쳣");
			}
		}
		return bookList;
	}
	
	/**
	 * ȡ������ͼ����Ϣ
	 * @return
	 */
	public List<Book> getAllBooks () {
		List<Book> bookList = new ArrayList<Book>();
		
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			String sql = " SELECT T_BOOK.BOOK_ID "
					   + "       ,T_BOOK.BOOK_NAME "
					   + "       ,T_BOOK.AUTHOR "
					   + "       ,T_BOOK.CLASSIFICATION "
					   + "       ,T_BOOK.POSITION "
					   + "       ,T_BOOK.PICTURE "
					   + "   FROM T_BOOK ";
	
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getString("BOOK_ID"));
				book.setBook_name(rs.getString("BOOK_NAME"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setClassification(rs.getString("CLASSIFICATION"));
				book.setPosition(rs.getString("POSITION"));
				book.setCoverPicture(rs.getString("PICTURE"));
				
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ִ�����ݲ����쳣");
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�PreparedStatement��Connection�쳣");
			}
		}
		return bookList;
	}
	
	/**
	 * ���ͼ��
	 * @param book
	 * @return
	 */
	public Boolean addBook (Book book) {
		Boolean result = false;
		
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			String sql = " INSERT INTO  T_BOOK (BOOK_ID, BOOK_NAME, AUTHOR, CLASSIFICATION, POSITION) "
					   + " VALUES (?, ?, ?, ?, ?) ";
	
			ps = conn.prepareStatement(sql);
			ps.setString(1, book.getBook_id());
			ps.setString(2, book.getBook_name());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getClassification());
			ps.setString(5, book.getPosition());
			int line = ps.executeUpdate();
			if (line != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ִ�����ݲ����쳣");
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�PreparedStatement��Connection�쳣");
			}
		}
		
		return result;
	}
	
	/**
	 * �޸�ͼ��
	 * @param book
	 * @return
	 */
	public Boolean modifyBook (Book book) {
		Boolean result = false;
		
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			String sql = " UPDATE T_BOOK"
					   + "    SET BOOK_NAME = ? "
					   + "       ,AUTHOR = ? "
					   + "       ,CLASSIFICATION = ? "
					   + "       ,POSITION = ? "
					   + "  WHERE BOOK_ID = ? ";
	
			ps = conn.prepareStatement(sql);
			ps.setString(1, book.getBook_name());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getClassification());
			ps.setString(4, book.getPosition());
			ps.setString(5, book.getBook_id());
			int line = ps.executeUpdate();
			if (line != 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ִ�����ݲ����쳣");
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�PreparedStatement��Connection�쳣");
			}
		}
		
		return result;
	}
	
	/**
	 * ���Ž���
	 * @return
	 */
	public List<Book> hotBook () {
		List<Book> bookList = new ArrayList<Book>();
		
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			String sql = " SELECT BOOK_ID "
					   + "       ,BOOK_NAME "
					   + "   FROM T_BOOK "
					   + "  ORDER BY NUMBER DESC, BOOK_ID ASC"
					   + "  LIMIT 0,7";
	
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getString("BOOK_ID"));
				book.setBook_name(rs.getString("BOOK_NAME"));
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ִ�����ݲ����쳣");
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�PreparedStatement��Connection�쳣");
			}
		}
		return bookList;
	}
	
	/**
	 * �����ϼ�
	 * @return
	 */
	public List<Book> newBook () {
		List<Book> bookList = new ArrayList<Book>();
		
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			String sql = " SELECT BOOK_ID "
					   + "       ,BOOK_NAME "
					   + "       ,PICTURE "
					   + "   FROM T_BOOK "
					   + "  ORDER BY ADD_DATE DESC, BOOK_ID ASC"
					   + "  LIMIT 0,3";
	
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getString("BOOK_ID"));
				book.setBook_name(rs.getString("BOOK_NAME"));
				book.setCoverPicture(rs.getString("PICTURE"));
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ִ�����ݲ����쳣");
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�PreparedStatement��Connection�쳣");
			}
		}
		return bookList;
	}
	
	/**
	 * ����ͼ��id��ѯͼ����Ϣ
	 * @param bookId
	 * @return
	 */
	public Book getSingleBook (String bookId) {
		Book book = null;
		
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			String sql = " SELECT BOOK_ID "
					   + "       ,BOOK_NAME "
					   + "       ,AUTHOR "
					   + "       ,CLASSIFICATION "
					   + "       ,POSITION "
					   + "       ,ADD_DATE "
					   + "       ,PICTURE "
					   + "   FROM T_BOOK "
					   + "  WHERE BOOK_ID = ? ";
	
			ps = conn.prepareStatement(sql);
			ps.setString(1, bookId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setBook_id(rs.getString("BOOK_ID"));
				book.setBook_name(rs.getString("BOOK_NAME"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setClassification(rs.getString("CLASSIFICATION"));
				book.setPosition(rs.getString("POSITION"));
				book.setAdd_date(rs.getDate("ADD_DATE"));
				book.setCoverPicture(rs.getString("PICTURE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ִ�����ݲ����쳣");
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�PreparedStatement��Connection�쳣");
			}
		}
		return book;
	}
	
	/**
	 * �û�����
	 * @param bookId
	 * @param userId
	 * @return
	 */
	public String borrowBook (String bookId, String userId) {
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false); // ���������Զ��ύ
			
			// ��ӽ�����Ϣ�������
			String sqlSelect = " INSERT INTO T_BORROWBOOK (USER_ID, BOOK_ID, BORROW_OUT_TIME) "
							 + " VALUES (?, ?, CURDATE()) ";
			
			ps = conn.prepareStatement(sqlSelect);
			ps.setString(1, userId);
			ps.setString(2, bookId);
			
			int lines = ps.executeUpdate();
			if (lines > 0) {
				conn.commit();
				return "success";
			} else {
				conn.rollback();
				System.out.println("ִ�����ݲ����쳣");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ִ�����ݲ����쳣");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("ִ�����ݲ����쳣");
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�PreparedStatement��Connection�쳣");
			}
		}
		return "����ʧ�ܣ��������Ա��ϵ��";
	}
	
	/**
	 * ��ѯͼ�飨ģ����ѯ��
	 * @param str
	 * @return
	 */
	public List<Book> searchBook (String str) {
		List<Book> bookList = new ArrayList<Book>();
		
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			String sql = " SELECT BOOK_ID "
					   + "       ,BOOK_NAME "
					   + "       ,AUTHOR "
					   + "       ,CLASSIFICATION "
					   + "       ,POSITION "
					   + "       ,ADD_DATE "
					   + "       ,PICTURE "
					   + "   FROM T_BOOK "
					   + "  WHERE (BOOK_NAME LIKE ?) "
					   + "     OR (AUTHOR LIKE ?) "
					   + "     OR (CLASSIFICATION LIKE ?) ";
	
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + str + "%");
			ps.setString(2, "%" + str + "%");
			ps.setString(3, "%" + str + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getString("BOOK_ID"));
				book.setBook_name(rs.getString("BOOK_NAME"));
				book.setAuthor(rs.getString("AUTHOR"));
				book.setClassification(rs.getString("CLASSIFICATION"));
				book.setPosition(rs.getString("POSITION"));
				book.setAdd_date(rs.getDate("ADD_DATE"));
				book.setCoverPicture(rs.getString("PICTURE"));
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ִ�����ݲ����쳣");
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�PreparedStatement��Connection�쳣");
			}
		}
		return bookList;
	}
}
