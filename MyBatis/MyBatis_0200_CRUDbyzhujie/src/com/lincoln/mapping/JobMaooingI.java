package com.lincoln.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lincoln.entity.Job;

/**
 * ����sqlӳ��Ľӿڣ�ʹ��ע��ָ������Ҫִ�е�SQL
 * @author lincoln
 *
 */
public interface JobMaooingI {
	//ʹ��@Insertע��ָ��add����Ҫִ�е�SQL
	@Insert("INSERT INTO T_JOB (job_name, salary) values (#{job_name} , #{salary})")
	public int add (Job job);
	
	//ʹ��@Deleteע��ָ��deleteById����Ҫִ�е�SQL
	@Delete("delete from t_job where job_id = #{job_id}")
	public int deleteById (int job_id);
	
	//ʹ��@Updateע��ָ��update����Ҫִ�е�SQL
	@Update("update t_job set job_name = #{job_name} ,salary = #{salary} where job_id = #{job_id}")
	public int update (Job job);
	
	//ʹ��@Selectע��ָ��getJobById����Ҫִ�е�SQL
	@Select("select * from t_job where job_id = #{job_id}")
	public Job getJobById (int job_id);
	
	//ʹ��@Selectע��ָ��getAll����Ҫִ�е�SQL
	@Select("select * from t_job")
	public List<Job> getAll();
}
