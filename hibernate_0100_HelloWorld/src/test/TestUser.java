package test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lincoln.entity.User;
import com.lincoln.util.GetSessionFactory;

public class TestUser {

	public static void main(String[] args) {
		// 调用工具类，取得sessionfactory
		SessionFactory sf = GetSessionFactory.getSessionFactory();
		Session session = sf.openSession();	
		
/*		User u1 = new User();
		u1.setUser_id("u002");
		u1.setUser_name("mary");
		u1.setPassword("123456");*/
		
		/*session.beginTransaction();
		session.save(u1);
		session.getTransaction().commit();*/
		
		String sql = "from User";
		//session.createSQLQuery(sql);
		List<User> allUser = session.createQuery(sql).list();
		for (User u:allUser) {
			System.out.println(u);
		}
		session.close();
		sf.close();
		
	}	

}
