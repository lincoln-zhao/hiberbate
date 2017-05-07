package com.ysu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static String username="root";
	private static String password="admin";
	private static String url = "jdbc:mysql://localhost:3306/graduation";//���ӵ�ַ
	
	/**
	 * ��������
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("����JDBC�����쳣��");
		}
	}
	
	/**
	 * ��ȡConnection����
	 */
	public static Connection getConnection () {
		Connection conn = null;
		
		try {
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ȡJDBC�����쳣��");
		}
		return conn;
	}
}
