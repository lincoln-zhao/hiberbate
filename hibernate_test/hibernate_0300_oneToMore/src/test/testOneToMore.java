package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lincoln.entity.Department;
import com.lincoln.entity.Employee;
import com.lincoln.util.GetSessionFactory;

public class testOneToMore {

	public static void main(String[] args) {
		// 调用工具类，取得sessionfactory
		SessionFactory sf = GetSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		
		session.getTransaction().begin();
		
		// 级联添加
/*		Department dept = new Department();
		dept.setdName("外交部");
		
		Employee emp = new Employee();
		emp.setEname("潘金莲");
		emp.setSalary(999.99);
		
		dept.getEmployeeSet().add(emp);
		//emp.setDepartment(dept);
		
		session.save(dept);
		//session.save(emp);
*/		
		
		// 级联删除
/*		Department dept= session.get(Department.class, 3);
		session.delete(dept);*/
		
		// 级联修改
		Department shangwu= session.get(Department.class, 1);
		Employee lvbu = session.get(Employee.class, 2);
		
		shangwu.getEmployeeSet().add(lvbu);
		lvbu.setDepartment(shangwu);
		
		
		
		
		
		session.getTransaction().commit();
		
		session.close();
		sf.close();
	}

}
