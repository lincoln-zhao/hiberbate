package com.lincoln.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lincoln.entity.Job;

/**
 * 定义sql映射的接口，使用注解指明方法要执行的SQL
 * @author lincoln
 *
 */
public interface JobMaooingI {
	//使用@Insert注解指明add方法要执行的SQL
	@Insert("INSERT INTO T_JOB (job_name, salary) values (#{job_name} , #{salary})")
	public int add (Job job);
	
	//使用@Delete注解指明deleteById方法要执行的SQL
	@Delete("delete from t_job where job_id = #{job_id}")
	public int deleteById (int job_id);
	
	//使用@Update注解指明update方法要执行的SQL
	@Update("update t_job set job_name = #{job_name} ,salary = #{salary} where job_id = #{job_id}")
	public int update (Job job);
	
	//使用@Select注解指明getJobById方法要执行的SQL
	@Select("select * from t_job where job_id = #{job_id}")
	public Job getJobById (int job_id);
	
	//使用@Select注解指明getAll方法要执行的SQL
	@Select("select * from t_job")
	public List<Job> getAll();
}
