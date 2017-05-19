package com.ysu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ysu.entity.News;
import com.ysu.util.DBUtil;

public class NewsDao {
	/**
	 * ��ҳ��ȡ����
	 * @return
	 */
	public List<News> getNews () {
		List<News> newsList = new ArrayList<News>();
		
		// ��ȡConnection����
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement ps = null;
		
		try {
			String sql = " SELECT NEWS_ID "
					   + "       ,TITLE "
					   + "   FROM T_NEWS "
					   + "  ORDER BY ADD_DATE DESC, NEWS_ID ASC"
					   + "  LIMIT 0,7";
	
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setNewsId(rs.getString("NEWS_ID"));
				news.setTitle(rs.getString("TITLE"));
				newsList.add(news);

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
		return newsList;
	}
}
