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
	 * 用户还书
	 * @param userId
	 * @param bookId
	 * @return
	 */
	public String returnBook (String userId, String bookId) {
		// 获取Connection连接
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false); // 设置事务不自动提交
			
			// 检查用户所借书与页面显示一致
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
					return "系统正在维护，请与管理员联系";
				}
			} else {
				ps.close();
				ps = null;
				return "系统正在维护，请与管理员联系";
			}
			
			ps.close();
			ps = null;
			
			// 取得借书时间与还书时间
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
			
			// 删除借书表
			String sqlDelUserBorrow = " DELETE FROM T_BORROWBOOK "
					 				+ "  WHERE BOOK_ID = ? "
					 				+ "    AND USER_ID = ? ";
			
			ps = conn.prepareStatement(sqlDelUserBorrow);
			ps.setString(1, bookId);
			ps.setString(2, userId);
			int lines = ps.executeUpdate();
			if (lines <= 0) {
				conn.rollback();
				return "系统正在维护，请与管理员联系";
			}
			
			// 更改图书表
			String sqlUpadeBook = " UPDATE T_BOOK "
								+ "    SET NUMBER = NUMBER + 1 "
								+ "  WHERE BOOK_ID = ? ";
			
			ps = conn.prepareStatement(sqlUpadeBook);
			ps.setString(1, bookId);
			lines = ps.executeUpdate();
			if (lines <= 0) {
				conn.rollback();
				return "系统正在维护，请与管理员联系";
			}
			
			// 更新用户曾借书表
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
				return "系统正在维护，请与管理员联系";
			}
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("执行数据操作异常");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("执行数据操作异常");
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
				System.out.println("关闭PreparedStatement、Connection异常");
			}
		}
		
		return "success";
	}
	
	public List<Book> getAllBorrowingBook () {
		List<Book> bookList = new ArrayList<Book>();
		
		// 获取Connection连接
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			String sql = " SELECT T_USER.USER_NAME "
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
				
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("执行数据操作异常");
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
				System.out.println("关闭PreparedStatement、Connection异常");
			}
		}
		return bookList;
	}
}
