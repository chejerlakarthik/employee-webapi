package com.karthik.rest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.karthik.rest.business.service.model.Employee;

public class EmployeeDao {
	
	protected Session getSession() {
		return SessionUtil.getSession();
	}
	
	protected Session beginTransaction() {
		Session session = getSession();
		session.beginTransaction();
		return session;
	}
	
	protected void commitTransaction(Session session) {
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Employee> getAll() {
		Session session = beginTransaction();
		@SuppressWarnings("unchecked")
		Query<Employee> query = getSession().createQuery("FROM Employee");
		List<Employee> list = query.list();
		commitTransaction(session);
		return list;
	}
	
	public Employee addEmployee(Employee employee) {
		getSession().beginTransaction();
		getSession().save(employee);
		getSession().getTransaction().commit();
		return employee;
	}

	public Employee get(Long empId) {
		Session session = beginTransaction();
		Employee employee = getSession().get(Employee.class, empId);
		commitTransaction(session);
		return employee;
	}

	public Employee update(Employee employee) {
		Session session = beginTransaction();
		getSession().persist(employee);
		commitTransaction(session);
		return employee;
	}

	public void remove(Long empId) {
		Session session = beginTransaction();
		Employee employee = getSession().get(Employee.class, empId);
		if (employee != null) {
			getSession().remove(employee);
		}
		commitTransaction(session);
	}

}
