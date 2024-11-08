package com.aurionpro.model;

import java.io.Serializable;

public class Supplier implements Serializable {

	private String supplierId;
	private String name;
	private String contactInfo;

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public Supplier(String supplierId, String name, String contactInfo) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.contactInfo = contactInfo;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", name=" + name + ", contactInfo=" + contactInfo + "]";
	}

	
}
