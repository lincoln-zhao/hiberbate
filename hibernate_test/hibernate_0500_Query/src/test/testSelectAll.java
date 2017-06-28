package test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.lincoln.entity.User;
import com.lincoln.util.GetSessionFactory;

public class testSelectAll {

	public static void main(String[] args) {
		// 调用工具类，取得sessionfactory
		SessionFactory sf = GetSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		//Session session = sf.getCurrentSession();
		
		// 1.query对象    HQL查询
/*		Query query = session.createQuery("from User order by user_id desc");
		
		List<User> userList = query.getResultList();
		
		for (User user : userList) {
			System.out.println(user);
		}*/
		
/*		Query query = session.createQuery("from User");
		
		query.setFirstResult(2);
		query.setMaxResults(2);
		
		List<User> userList = query.getResultList();
		
		for (User user : userList) {
			System.out.println(user);
		}*/
		
/*		Query query = session.createQuery("select avg(user_id) from User");
		
		Object obj = query.uniqueResult();
		
		System.out.println(obj);
		
		Long lobj = (Long) obj;
		
		System.out.println(lobj);
		
		int count = lobj.intValue();
		
		System.out.println(count);*/
		
		// 2.criteria  QBC查询
/*		Criteria criteria = session.createCriteria(User.class);
		List<User> userList = criteria.list();
		
		for (User user : userList) {
			System.out.println(user);
		}*/
		
/*		Criteria criteria = session.createCriteria(User.class);
		
//		criteria.add(Restrictions.eq("user_id", 1));
//		criteria.add(Restrictions.or(Restrictions.eq("user_id", 2), Restrictions.like("user_name", "%lin%")));
		criteria.add(Restrictions.or(Restrictions.or(Restrictions.eq("user_id", 2), Restrictions.like("user_name", "%lin%")),Restrictions.or(Restrictions.eq("user_id", 3))));
		
		criteria.addOrder(Order.asc("user_id"));
		
		criteria.setFirstResult(0);
		criteria.setMaxResults(2);
		
		List<User> userList = criteria.list();
		
		for (User user : userList) {
			System.out.println(user);
		}*/
		// 统计查询
		Criteria criteria = session.createCriteria(User.class);
		// 设置操作
		criteria.setProjection(Projections.avg("user_id"));
		Object obj = criteria.uniqueResult();
		
		System.out.println(obj);
		
		
		
		// 3.
/*		SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");
		sqlQuery.addEntity(User.class);
		List<User> userList = sqlQuery.list();
		for (User user : userList) {
			System.out.println(user);
		}*/
		
		
		session.close();
		sf.close();
	}

}
