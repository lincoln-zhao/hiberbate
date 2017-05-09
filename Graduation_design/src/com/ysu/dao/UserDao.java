package com.ysu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ysu.entity.User;
import com.ysu.util.DBUtil;

public class UserDao {
	
	/**
	 * �û���¼
	 * @param UserName
	 * @param password
	 * @return
	 */
	public User userLogin (String UserName, String password) {
		User user = null;
		
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		// ��ѯ���ݿ�
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
			System.out.println("��ѯ�����쳣");
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
		
		return user;
	}
	
	/**
	 * �û�ע��
	 * @param user
	 * @return
	 */
	public boolean userRegister (User user) {
		boolean result = false;
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		// �ж��û��Ƿ����
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
					System.out.println("�ر�PreparedStatement��Connection�쳣");
				}
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ѯ�����쳣");
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException ee) {
				ee.printStackTrace();
				System.out.println("�ر�PreparedStatement��Connection�쳣");
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�PreparedStatement��Connection�쳣");
			}
		}
		
		// �����ݿ��������
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
			System.out.println("��������쳣");
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
}
