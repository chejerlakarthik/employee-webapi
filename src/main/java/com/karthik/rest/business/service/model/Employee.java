package com.karthik.rest.business.service.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement @Entity @Table(name="EMPLOYEE")
public class Employee extends RestResource {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long empId;
	private String empName;
	private Date lastModified;
	
	// The no-arg constructor is a must for Serialization - JSON or XML.
	public Employee() {}
	
	public Employee(Long empId, String empName) {
		this.empId = empId;
		this.empName = empName;
		this.lastModified = new Date();
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

}
