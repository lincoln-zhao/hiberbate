package com.ysu.service;

import java.util.List;

import com.ysu.dao.NewsDao;
import com.ysu.entity.News;

public class NewsService {
	NewsDao newsDao = new NewsDao();
	
	public List<News> getNews () {
		return newsDao.getNews();
	}
}
