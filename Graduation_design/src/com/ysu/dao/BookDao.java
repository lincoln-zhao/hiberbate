package com.ysu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			String sqlSelect = " SELECT T_USER.BOOK_ID "
							 + "   FROM T_USER "
							 + "  WHERE T_USER.USER_ID = ?";
			
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
			
			// �����û���
			String sqlUpadeUser = " UPDATE T_USER "
								+ "    SET BOOK_ID = '' "
								+ "  WHERE USER_ID = ? ";
			
			ps = conn.prepareStatement(sqlUpadeUser);
			ps.setInt(1, Integer.parseInt(userId));
			int lines = ps.executeUpdate();
			if (lines <= 0) {
				conn.rollback();
				return "ϵͳ����ά�����������Ա��ϵ";
			}
			
			// ȡ�ý���ʱ���뻹��ʱ��
			Date borrowDate = null;
			Date returnDate = null;
			String sqlSelectTime = " SELECT BORROW_OUT_TIME "
								 + "       ,SYSDATE() AS RETURN_TIME "
								 + "   FROM T_BOOK "
								 + "  WHERE BOOK_ID = ? ";
			
			ps = conn.prepareStatement(sqlSelectTime);
			ps.setString(1, bookId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				borrowDate = rs.getDate("BORROW_OUT_TIME");
				returnDate = rs.getDate("RETURN_TIME");
			}
			
			// ����ͼ���
			String sqlUpadeBook = " UPDATE T_BOOK "
								+ "    SET BORROW_OUT_TIME = null "
								+ "       ,NUMBER = NUMBER + 1 "
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
}
