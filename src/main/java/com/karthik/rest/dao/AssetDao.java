package com.karthik.rest.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.karthik.rest.business.service.model.Asset;
import com.karthik.rest.business.service.model.Employee;
import com.karthik.rest.exception.DoesNotExistException;

public class AssetDao {
	
	public List<Asset> getAll(Long empId) throws DoesNotExistException {
		Session session = SessionUtil.beginTransaction();
		Employee employee = session.get(Employee.class, empId);
		if (employee == null) {
			throw new DoesNotExistException("Employee " + empId + " does not exist");
		}
		SessionUtil.commitTransaction(session);
		session = null;
		
		session = SessionUtil.beginTransaction();
		Query<Asset> query = session.createQuery("select a from Asset a join fetch a.employee where a.employee.empId = :employeeId", Asset.class)
									.setParameter("employeeId", employee.getEmpId());
		List<Asset> employeeAssets = query.getResultList();
		SessionUtil.commitTransaction(session);
		return employeeAssets;
	}
	
	public Asset tagAssetToEmployee(Long empId, Asset asset) throws DoesNotExistException {
		Session session = SessionUtil.beginTransaction();
		Employee employee = session.get(Employee.class, empId);
		if (employee == null) {
			throw new DoesNotExistException("Employee " + empId + " does not exist");
		}
		asset.setAssetTaggedDate(new Date());
		session.persist(asset);
		
		employee.getAssets().add(asset);
		asset.setEmployee(employee);
		session.merge(employee);
		SessionUtil.commitTransaction(session);
		return asset;
	}

	public Map<Integer, Asset> get(Long empId) {
		// TODO Auto-generated method stub
		return null;
	}

}
