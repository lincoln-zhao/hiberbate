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
		//mybatis�������ļ�
        String resource = "conf.xml";
        //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        InputStream is = MainTest.class.getClassLoader().getResourceAsStream(resource);
        //����sqlSession�Ĺ���
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        //Reader reader = Resources.getResourceAsReader(resource); 
        //����sqlSession�Ĺ���
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //������ִ��ӳ���ļ���sql��sqlSession
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
		job.setJob_name("ũҩ");
		job.setSalary(10.01);
		
		JobMaooingI jobMaooingI = session.getMapper(JobMaooingI.class);
		
		int result = jobMaooingI.add(job);
		
		session.commit();
		
		System.out.println(result);
	}
	
	public void testUpdate (SqlSession session) {
		String statement = "com.lincoln.mapping.jobMapping.getJobById";//ӳ��sql�ı�ʶ�ַ���
        //ִ�в�ѯ����һ��ΨһJob�����sql
        Job job = (Job) session.selectOne(statement, 4);
        System.out.println(job);
        
        statement = "com.lincoln.mapping.jobMapping.updateJobById";//ӳ��sql�ı�ʶ�ַ���
        job.setJob_name("play");
        job.setSalary(11.11);
        int result = session.update(statement, job);
        System.out.println(result);
        
        session.commit();
	}
	
	public void testDelete (SqlSession session) {
		String statement = "com.lincoln.mapping.jobMapping.deleteJobById";//ӳ��sql�ı�ʶ�ַ���
		int result = session.delete(statement, 4);
        System.out.println(result);
        session.commit();
		
	}
}
