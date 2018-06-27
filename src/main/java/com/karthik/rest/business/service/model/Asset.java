package com.karthik.rest.business.service.model;

import java.util.Date;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity @Table(name="ASSET")
public class Asset extends RestResource{

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int assetId;
	private String assetName;
	private String assetDescription;
	private Date assetTaggedDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonbTransient @XmlTransient
	private Employee employee;
	
	public Asset() {}
	
	public Asset(int assetId, String assetName, String assetDescription) {
		this.assetId = assetId;
		this.assetName = assetName;
		this.assetDescription = assetDescription;
		this.assetTaggedDate = new Date();
	}

	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetDescription() {
		return assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public Date getAssetTaggedDate() {
		return assetTaggedDate;
	}

	public void setAssetTaggedDate(Date assetTaggedDate) {
		this.assetTaggedDate = assetTaggedDate;
	}

	@Override
	public String toString() {
		return "Asset [assetId=" + assetId + ", assetName=" + assetName + ", assetDescription=" + assetDescription
				+ ", assetTaggedDate=" + assetTaggedDate + "]";
	}

}
