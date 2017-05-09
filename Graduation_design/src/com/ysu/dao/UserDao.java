package com.ysu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
