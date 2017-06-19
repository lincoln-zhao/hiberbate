package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lincoln.util.GetSessionFactory;

public class testMoreToMore {

	public static void main(String[] args) {
		// 调用工具类，取得sessionfactory
		SessionFactory sf = GetSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		
		session.getTransaction().begin();
		
		
		
		
		
		
		session.getTransaction().commit();
		
		session.close();
		sf.close();
	}

}
