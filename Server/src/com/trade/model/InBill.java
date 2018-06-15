package com.trade.model;

public class InBill {

	private String inBillId;
	private String goodsId;
	private String goodsName;
	private String goodsUnit;
	private String goodsInUnitPrice;
	private String goodsImage;
	private String goodsCount;
	private String totalPrice;
	private String supplierId;
	private String supplierName;
	private String supplierPhone;
	private String supplierAddress;
	private String time;

	public InBill(String inBillId, String goodsId, String goodsName,
			String goodsUnit, String goodsImage, String goodsInUnitPrice,
			String goodsCount, String totalPrice, String supplierId,
			String supplierName, String supplierPhone,
			String supplierAddress, String time) {
		super();
		this.inBillId = inBillId;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsUnit = goodsUnit;
		this.goodsInUnitPrice = goodsInUnitPrice;
		this.goodsImage = goodsImage;
		this.goodsCount = goodsCount;
		this.totalPrice = totalPrice;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierPhone = supplierPhone;
		this.supplierAddress = supplierAddress;
		this.time = time;
	}

	public String getInBillId() {
		return inBillId;
	}

	public void setInBillId(String inBillId) {
		this.inBillId = inBillId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getGoodsInUnitPrice() {
		return goodsInUnitPrice;
	}

	public void setGoodsInUnitPrice(String goodsInUnitPrice) {
		this.goodsInUnitPrice = goodsInUnitPrice;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public String getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
