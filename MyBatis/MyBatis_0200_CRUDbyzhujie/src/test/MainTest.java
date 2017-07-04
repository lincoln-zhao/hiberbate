package test;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.lincoln.entity.Job;
import com.lincoln.mapping.JobMaooingI;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//mybatis的配置文件
        String resource = "conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = MainTest.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();

        MainTest mt = new MainTest();
        mt.testInsert(session);
	}
	
	public void testSelect (SqlSession session) {
		JobMaooingI jobMaooingI = session.getMapper(JobMaooingI.class);
		Job job = jobMaooingI.getJobById(1);
		System.out.println(job);
	}

	
	public void testInsert (SqlSession session) {
		Job job = new Job();
		job.setJob_name("农药");
		job.setSalary(10.01);
		
		JobMaooingI jobMaooingI = session.getMapper(JobMaooingI.class);
		
		int result = jobMaooingI.add(job);
		
		session.commit();
		
		System.out.println(result);
	}
	
	public void testUpdate (SqlSession session) {
		String statement = "com.lincoln.mapping.jobMapping.getJobById";//映射sql的标识字符串
        //执行查询返回一个唯一Job对象的sql
        Job job = (Job) session.selectOne(statement, 4);
        System.out.println(job);
        
        statement = "com.lincoln.mapping.jobMapping.updateJobById";//映射sql的标识字符串
        job.setJob_name("play");
        job.setSalary(11.11);
        int result = session.update(statement, job);
        System.out.println(result);
        
        session.commit();
	}
	
	public void testDelete (SqlSession session) {
		String statement = "com.lincoln.mapping.jobMapping.deleteJobById";//映射sql的标识字符串
		int result = session.delete(statement, 4);
        System.out.println(result);
        session.commit();
		
	}
}
