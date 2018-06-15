package com.trade.model;

public class Supplier {

	private String supplierId;
	private String name;
	private String phone;
	private String address;

	public Supplier(String supplierId, String name, String phone, String address) {
		super();
		this.supplierId = supplierId;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
