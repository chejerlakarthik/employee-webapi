package com.karthik.rest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.karthik.rest.business.service.model.Employee;

public class EmployeeDao {
	
	public List<Employee> getAll(){
		
		@SuppressWarnings("unchecked")
		Query<Employee> query = getSession().createQuery("FROM Employee");
		List<Employee> list = query.list();
		getSession().close();
		
		return list;
	}
	
	public Employee addEmployee(Employee employee) {
		beginTransaction();
		getSession().save(employee);
		commitTransaction();
		return employee;
	}

	protected Session getSession() {
		return SessionUtil.getSession();
	}
	
	protected void beginTransaction() {
		getSession().getTransaction().begin();
	}
	
	protected void commitTransaction() {
		getSession().getTransaction().commit();
		getSession().close();
	}

	public Employee get(Long empId) {
		Employee employee = getSession().get(Employee.class, empId);
		getSession().close();
		return employee;
	}

	public Employee update(Employee employee) {
		beginTransaction();
		getSession().persist(employee);
		commitTransaction();
		return employee;
	}

	public void remove(Long empId) {
		beginTransaction();
		Employee employee = getSession().get(Employee.class, empId);
		if (employee != null) {
			getSession().remove(employee);
		}
		commitTransaction();
	}

}
