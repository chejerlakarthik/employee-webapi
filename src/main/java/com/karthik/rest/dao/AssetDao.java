package com.karthik.rest.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.karthik.rest.business.service.model.Asset;
import com.karthik.rest.business.service.model.Employee;
import com.karthik.rest.exception.DoesNotExistException;

public class AssetDao {

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

	public List<Asset> getAll(Long empId) throws DoesNotExistException {
		Session session = beginTransaction();
		Employee employee = getSession().get(Employee.class, empId);
		if (employee == null) {
			throw new DoesNotExistException("Employee " + empId + " does not exist");
		}
		Collection<Asset> employeeAssets = employee.getAssets();
		commitTransaction(session);
		return employeeAssets.isEmpty() ? new ArrayList<Asset>() : new ArrayList<Asset>() ;
	}
	
	public Asset tagAssetToEmployee(Long empId, Asset asset) {
		Employee employee = getSession().get(Employee.class, empId);
		employee.getAssets().add(asset);
		getSession().save(employee);
		return asset;
	}

	public Map<Integer, Asset> get(Long empId) {
		// TODO Auto-generated method stub
		return null;
	}

}
