package com.ysu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ysu.entity.Admin;
import com.ysu.entity.Book;
import com.ysu.entity.User;
import com.ysu.util.DBUtil;

public class UserDao {
	
	/**
	 * 用户登录
	 * @param UserName
	 * @param password
	 * @return
	 */
	public User userLogin (String UserName, String password) {
		User user = null;
		
		// 获取Connection连接
		Connection conn = DBUtil.getConnection();
		
		// 查询数据库
		PreparedStatement ps = null;
		String sql = "SELECT USER_ID "
				   + "      ,USER_NAME "
				   + "      ,PASSWORD "
				   + "      ,SEX "
				   + "      ,PHONE "
				   + "  FROM T_USER "
				   + " WHERE USER_NAME = ? "
				   + "   AND PASSWORD = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, UserName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUser_id(rs.getString("USER_ID"));
				user.setUser_name(rs.getString("USER_NAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setSex(rs.getString("SEX"));
				user.setPhone(rs.getString("PHONE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据异常");
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
		
		return user;
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public boolean userRegister (User user) {
		boolean result = false;
		// 获取Connection连接
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		// 判断用户是否存在
		String selectSql = " SELECT USER_ID "
						 + "   FROM T_USER "
						 + "  WHERE USER_NAME = ? ";
		try {
			ps = conn.prepareStatement(selectSql);
			ps.setString(1, user.getUser_name());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				try {
					if (conn != null) {
						conn.close();
						conn = null;
					}
				} catch (SQLException ee) {
					ee.printStackTrace();
					System.out.println("关闭PreparedStatement、Connection异常");
				}
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据异常");
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException ee) {
				ee.printStackTrace();
				System.out.println("关闭PreparedStatement、Connection异常");
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭PreparedStatement、Connection异常");
			}
		}
		
		// 向数据库添加数据
		String sql = " INSERT INTO T_USER (USER_NAME, PASSWORD, SEX, PHONE) "
				   + " VALUES (?, ?, ?, ?) ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUser_name());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getSex());
			ps.setString(4, user.getPhone());
			int line = ps.executeUpdate();
			if (line > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("添加数据异常");
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
		return result;
	}
	
	/**
	 * 用户曾借书
	 * @param UserId
	 */
	public List<Book> borrowdBook (String userId) {
		List<Book> bookList = new ArrayList<Book>();
		// 获取Connection连接
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		String sql = " SELECT T_BOOK.BOOK_ID "
				   + "       ,T_BOOK.BOOK_NAME "
				   + "       ,T_BORROWBOOKHISTORY.START_DATE "
				   + "       ,T_BORROWBOOKHISTORY.END_DATE "
				   + "   FROM T_BORROWBOOKHISTORY, T_BOOK "
				   + "  WHERE T_BORROWBOOKHISTORY.BOOK_ID = T_BOOK.BOOK_ID "
				   + "    AND T_BORROWBOOKHISTORY.USER_ID = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(userId));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getString("BOOK_ID"));
				book.setBook_name(rs.getString("BOOK_NAME"));
				book.setStart_date(rs.getDate("START_DATE"));
				book.setEnd_date(rs.getDate("END_DATE"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据异常");
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
	
	/**
	 * 用户现借书
	 * @param UserId
	 * @return
	 */
	public List<Book> nowBorrowBook (String userId) {
		List<Book> nowBookList = new ArrayList<Book>();
		// 获取Connection连接
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		String sql = " SELECT T_BOOK.BOOK_ID "
				   + "       ,T_BOOK.BOOK_NAME "
				   + "       ,T_BORROWBOOK.BORROW_OUT_TIME "
				   + "       ,DATE_ADD(T_BORROWBOOK.BORROW_OUT_TIME, INTERVAL 7 DAY) AS RETURN_TIME "
				   + "   FROM T_BORROWBOOK, T_BOOK "
				   + "  WHERE T_BORROWBOOK.BOOK_ID = T_BOOK.BOOK_ID "
				   + "    AND T_BORROWBOOK.USER_ID = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(userId));
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getString("BOOK_ID"));
				book.setBook_name(rs.getString("BOOK_NAME"));
				book.setStart_date(rs.getDate("BORROW_OUT_TIME"));
				book.setEnd_date(rs.getDate("RETURN_TIME"));
				nowBookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据异常");
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
		
		
		return nowBookList;
	}
	
	/**
	 * 管理员登录
	 * @param adminrName
	 * @param password
	 * @return
	 */
	public Admin adminLogin (String adminrName, String password) {
		// 创建管理员对象
		Admin admin = null;
		
		// 获取Connection连接
		Connection conn = DBUtil.getConnection();
		
		// 查询数据库
		PreparedStatement ps = null;
		String sql = "SELECT ADMIN_ID "
				   + "      ,ADMIN_NAME "
				   + "      ,PASSWORD "
				   + "  FROM T_ADMIN "
				   + " WHERE ADMIN_NAME = ? "
				   + "   AND PASSWORD = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, adminrName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin.setAdmin_id(rs.getString("ADMIN_ID"));
				admin.setAdmin_name(rs.getString("ADMIN_NAME"));
				admin.setPassword(rs.getString("PASSWORD"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据异常");
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
		
		return admin;
	}
	
	/**
	 * 取得所用用户
	 * @return
	 */
	public List<User> getAllUsers () {
		List<User> userList = new ArrayList<User>();
		// 获取Connection连接
		Connection conn = DBUtil.getConnection();
		
		// 查询数据库
		PreparedStatement ps = null;
		String sql = "SELECT USER_ID "
				   + "      ,uSER_NAME "
				   + "      ,PASSWORD "
				   + "      ,SEX "
				   + "      ,PHONE "
				   + "  FROM T_USER ";
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getString("USER_ID"));
				user.setUser_name(rs.getString("USER_NAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setSex(rs.getString("SEX"));
				user.setPhone(rs.getString("PHONE"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据异常");
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
		
		return userList;
	}
	
	/**
	 * 删除用户
	 * @param userId
	 */
	public boolean delUser (String userId) {
		boolean result = false;
		// 获取Connection连接
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		// 判断用户是否存在
		String selectSql = " SELECT USER_ID "
						 + "   FROM T_USER "
						 + "  WHERE USER_ID = ? ";
		try {
			ps = conn.prepareStatement(selectSql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				try {
					if (conn != null) {
						conn.close();
						conn = null;
					}
				} catch (SQLException ee) {
					ee.printStackTrace();
					System.out.println("关闭PreparedStatement、Connection异常");
				}
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据异常");
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException ee) {
				ee.printStackTrace();
				System.out.println("关闭PreparedStatement、Connection异常");
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭PreparedStatement、Connection异常");
			}
		}
		
		// 向数据库添加数据
		String sql = " DELETE FROM T_USER "
				   + " WHERE USER_ID = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			int line = ps.executeUpdate();
			if (line > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("添加数据异常");
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
		return result;
	}
}
