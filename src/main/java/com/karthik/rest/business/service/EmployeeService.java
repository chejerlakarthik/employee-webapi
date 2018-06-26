package com.karthik.rest.business.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.karthik.rest.business.service.model.Employee;
import com.karthik.rest.dao.EmployeeDao;
import com.karthik.rest.exception.BadRequestException;
import com.karthik.rest.exception.DoesNotExistException;

public class EmployeeService {
	
	private EmployeeDao employeeDao = new EmployeeDao();

	public Employee add(Employee employee) {
		employee.setLastModified(new Date());
		return employeeDao.addEmployee(employee);
	}
	
	public Employee read(Long empId) throws DoesNotExistException {
		Employee employee = employeeDao.get(empId);
		if (employee == null)
			throw new DoesNotExistException("Employee " + empId +" does not exist");
		return employee;
	}
	
	public List<Employee> readAll() {
		return employeeDao.getAll();
	}
	
	public List<Employee> readAllFilteredByYear(Integer year) {
		assert (year != null && year > 0);
		List<Employee> employeesByYear = new ArrayList<Employee>();

		for (Employee employee : employeeDao.getAll()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(employee.getLastModified());
			if (cal.get(Calendar.YEAR) == year) {
				employeesByYear.add(employee);
			}
		}
		return employeesByYear;
	}
	
	public List<Employee> readAllPaginated(Integer start, Integer pagesize) {
		assert (start > 0 && pagesize > 0);
		
		if (pagesize > employeeDao.getAll().size())
			pagesize = employeeDao.getAll().size();
		
		return employeeDao.getAll().subList(start-1, start + pagesize -1);
	}
	
	public Employee update(Long empId, Employee employee) throws DoesNotExistException, BadRequestException {
		Employee emp = employeeDao.get(empId);
		
		if (emp == null) {
			throw new DoesNotExistException("Employee " + empId +" does not exist");
		}
		
		if (!(employee instanceof Employee)) {
			throw new BadRequestException("Bad request data");
		}
		
		employee.setLastModified(new Date());
		
		return employeeDao.update(employee); 
	}
	
	public void delete(Long empId) {
		employeeDao.remove(empId);
	}

}
