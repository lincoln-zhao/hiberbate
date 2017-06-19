package com.lincoln.entity;

import java.util.HashSet;
import java.util.Set;

public class Department {
	private Integer did;  // 部门id
	private String dName; // 部门名称
	private Set<Employee> employeeSet = new HashSet<Employee>(); // 部门内所有员工
	
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public Set<Employee> getEmployeeSet() {
		return employeeSet;
	}
	public void setEmployeeSet(Set<Employee> employeeSet) {
		this.employeeSet = employeeSet;
	}
}
