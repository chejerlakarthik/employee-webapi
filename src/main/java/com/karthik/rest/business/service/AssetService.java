package com.karthik.rest.business.service;

import java.util.ArrayList;
import java.util.List;

import com.karthik.rest.business.service.model.Asset;
import com.karthik.rest.dao.AssetDao;
import com.karthik.rest.exception.DoesNotExistException;

public class AssetService {

	private AssetDao assetDao = new AssetDao();

	public List<Asset> readAll(Long empId) throws DoesNotExistException {
		List<Asset> employeeAssets = assetDao.getAll(empId);
		if (employeeAssets == null) {
			throw new DoesNotExistException("No assets tagged to employee ID " + empId);
		}
		return new ArrayList<Asset>();
	}

	public Asset read(Long empId, Integer assetId) throws DoesNotExistException {
		throw new RuntimeException("Not Implemented");
	}

	public Asset tagAssetToEmployee(Long empId, Asset asset) {
		throw new RuntimeException("Not Implemented");
	}

}
