package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lincoln.entity.Role;
import com.lincoln.entity.User;
import com.lincoln.util.GetSessionFactory;

public class testMoreToMore {

	public static void main(String[] args) {
		// 调用工具类，取得sessionfactory
		SessionFactory sf = GetSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		
		session.getTransaction().begin();
		
		
		User u1 = new User();
		u1.setUser_name("lincoln");
		u1.setPassword("123");
		
		User u2 = new User();
		u2.setUser_name("mary");
		u2.setPassword("456");
		
		Role r1 = new Role();
		r1.setRole_name("主人");
		
		Role r2 = new Role();
		r2.setRole_name("女仆");
		
		Role r3 = new Role();
		r3.setRole_name("护士");
		
		u1.getRoleSet().add(r1);
		u1.getRoleSet().add(r2);
		
		u2.getRoleSet().add(r2);
		u2.getRoleSet().add(r3);
		
		
		session.save(u1);
		session.save(u2);
		
		
		
		session.getTransaction().commit();
		
		session.close();
		sf.close();
	}

}
