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
	 * 首页获取新闻
	 * @return
	 */
	public List<News> getNews () {
		List<News> newsList = new ArrayList<News>();
		
		// 获取Connection连接
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
		return newsList;
	}
}
