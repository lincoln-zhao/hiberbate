package com.lincoln.entity;

public class Job {
	private int job_id;
	private String job_name;
	private Double salary;
	
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Job [job_id=" + job_id + ", job_name=" + job_name + ", salary=" + salary + "]";
	}
	

}
