package com.ysu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static String username="root";
	private static String password="admin";
	private static String url = "jdbc:mysql://localhost:3306/graduation";//连接地址
	
	/**
	 * 加载驱动
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("加载JDBC驱动异常！");
		}
	}
	
	/**
	 * 获取Connection连接
	 */
	public static Connection getConnection () {
		Connection conn = null;
		
		try {
			conn = (Connection) DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取JDBC链接异常！");
		}
		return conn;
	}
}
