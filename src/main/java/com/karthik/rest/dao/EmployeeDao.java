package com.karthik.rest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.karthik.rest.business.service.model.Employee;

public class EmployeeDao {
	
	public List<Employee> getAll() {
		Session session = SessionUtil.beginTransaction();
		@SuppressWarnings("unchecked")
		Query<Employee> query = session.createQuery("FROM Employee");
		List<Employee> list = query.list();
		SessionUtil.commitTransaction(session);
		return list;
	}
	
	public Employee addEmployee(Employee employee) {
		Session session = SessionUtil.beginTransaction();
		session.save(employee);
		session.getTransaction().commit();
		return employee;
	}

	public Employee get(Long empId) {
		Session session = SessionUtil.beginTransaction();
		Employee employee = session.get(Employee.class, empId);
		SessionUtil.commitTransaction(session);
		return employee;
	}

	public Employee update(Employee employee) {
		Session session = SessionUtil.beginTransaction();
		session.persist(employee);
		SessionUtil.commitTransaction(session);
		return employee;
	}

	public void remove(Long empId) {
		Session session = SessionUtil.beginTransaction();
		Employee employee = session.get(Employee.class, empId);
		if (employee != null) {
			session.remove(employee);
		}
		SessionUtil.commitTransaction(session);
	}

}
