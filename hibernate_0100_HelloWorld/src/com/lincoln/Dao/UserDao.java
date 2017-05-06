package com.lincoln.Dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lincoln.entity.User;
import com.lincoln.util.GetSessionFactory;

public class UserDao {
	/**
	 * 取得全部user
	 * @return
	 */
	public List<User> getAllUser () {
		// 调用工具类，取得sessionfactory
		SessionFactory sf = GetSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		
		// 查全部user数据
		List<User> allUser = session.createQuery("from User").list();
		
		session.close();
		
		return allUser;
	}
	
	/**
	 * 添加user
	 * @return
	 */
	public boolean addUser (User user) {
		boolean returnBoolean = false;
		// 调用工具类，取得sessionfactory
		SessionFactory sf = GetSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		
		// 向数据库添加数据
		Transaction transaction = null;
		Serializable result = null;
		try {
			// 开启事物
			transaction = session.beginTransaction();
			// 添加数据
			result = session.save(user);
			// 提交事物
			transaction.commit();
			
			if (result != null) {
				returnBoolean = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}

		return returnBoolean;
	}
	
	/**
	 * 根据userId删除user
	 * @param user
	 * @return
	 */
	public boolean deleteUser (User user) {
		boolean returnBoolean = false;
		// 调用工具类，取得sessionfactory
		SessionFactory sf = GetSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		
		// 删除数据库数据
		Transaction transaction = null;
		try {
			// 开启事物
			transaction = session.beginTransaction();
			// 添加数据
			session.delete(user);
			// 提交事物
			transaction.commit();
			
			returnBoolean = true;

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return returnBoolean;
	}
	
	/**
	 * 根据userId修改user
	 * @param user
	 * @return
	 */
	public boolean modifyUser (User user) {
		boolean returnBoolean = false;
		// 调用工具类，取得sessionfactory
		SessionFactory sf = GetSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		
		// 修改数据库添加数据
		Transaction transaction = null;
		try {
			// 开启事物
			transaction = session.beginTransaction();
			// 添加数据
			session.update(user);
			// 提交事物
			transaction.commit();
			
			returnBoolean = true;

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return returnBoolean;
	}
}
