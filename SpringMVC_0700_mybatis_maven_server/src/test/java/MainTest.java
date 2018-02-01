

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.lincoln.user.entity.User;

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
        mt.testSelect(session);
	}
	
	public void testSelect (SqlSession session) {
        /**
         * ӳ��sql�ı�ʶ�ַ�����
         * me.gacl.mapping.userMapper��userMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ��
         * getUser��select��ǩ��id����ֵ��ͨ��select��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL
         */
        String statement = "com.lincoln.user.mappers.UserMapper.getUserById";//ӳ��sql�ı�ʶ�ַ���
        //ִ�в�ѯ����һ��ΨһJob�����sql
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
//		String statement = "com.lincoln.mapping.jobMapping.insertJob";//ӳ��sql�ı�ʶ�ַ���
//		
//		int result = session.insert(statement, job);
//		
//		session.commit();
//		
//		System.out.println(result);
//	}
//	
//	public void testUpdate (SqlSession session) {
//		String statement = "com.lincoln.mapping.jobMapping.getJobById";//ӳ��sql�ı�ʶ�ַ���
//        //ִ�в�ѯ����һ��ΨһJob�����sql
//        Job job = (Job) session.selectOne(statement, 4);
//        System.out.println(job);
//        
//        statement = "com.lincoln.mapping.jobMapping.updateJobById";//ӳ��sql�ı�ʶ�ַ���
//        job.setJob_name("play");
//        job.setSalary(11.11);
//        int result = session.update(statement, job);
//        System.out.println(result);
//        
//        session.commit();
//	}
//	
//	public void testDelete (SqlSession session) {
//		String statement = "com.lincoln.mapping.jobMapping.deleteJobById";//ӳ��sql�ı�ʶ�ַ���
//		int result = session.delete(statement, 4);
//        System.out.println(result);
//        session.commit();
//		
//	}
}
