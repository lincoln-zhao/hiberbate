

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.lincoln.user.entity.User;

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
        mt.testSelect(session);
	}
	
	public void testSelect (SqlSession session) {
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.lincoln.user.mappers.UserMapper.getUserById";//映射sql的标识字符串
        //执行查询返回一个唯一Job对象的sql
        User job = (User) session.selectOne(statement, "1");
        System.out.println(job.getId());
        System.out.println(job.getName());
        System.out.println(job.getPassword());
	}

	
//	public void testInsert (SqlSession session) {
//		Job job = new Job();
//		job.setJob_name("paly");
//		job.setSalary(12.5);
//		
//		String statement = "com.lincoln.mapping.jobMapping.insertJob";//映射sql的标识字符串
//		
//		int result = session.insert(statement, job);
//		
//		session.commit();
//		
//		System.out.println(result);
//	}
//	
//	public void testUpdate (SqlSession session) {
//		String statement = "com.lincoln.mapping.jobMapping.getJobById";//映射sql的标识字符串
//        //执行查询返回一个唯一Job对象的sql
//        Job job = (Job) session.selectOne(statement, 4);
//        System.out.println(job);
//        
//        statement = "com.lincoln.mapping.jobMapping.updateJobById";//映射sql的标识字符串
//        job.setJob_name("play");
//        job.setSalary(11.11);
//        int result = session.update(statement, job);
//        System.out.println(result);
//        
//        session.commit();
//	}
//	
//	public void testDelete (SqlSession session) {
//		String statement = "com.lincoln.mapping.jobMapping.deleteJobById";//映射sql的标识字符串
//		int result = session.delete(statement, 4);
//        System.out.println(result);
//        session.commit();
//		
//	}
}
