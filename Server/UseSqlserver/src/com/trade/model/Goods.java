package com.trade.model;

public class Goods {

	private String goodsId;
	private String name;
	private String unit;
	private String inUnitPrice;
	private String outUnitPrice;
	private String image;
	private String repertory;
	private String supplierId;
	private String supplierName;
	private String supplierPhone;
	private String supplierAddress;

	public Goods(String goodsId, String goodsName, String goodsUnit,
			String goodsInUnitPrice, String goodsOutUnitPrice,
			String goodsImage, String repertory, String goodsSupplierId,
			String goodsSupplierName, String goodsSupplierPhone,
			String goodsSupplierAddress) {
		super();
		this.goodsId = goodsId;
		this.name = goodsName;
		this.unit = goodsUnit;
		this.inUnitPrice = goodsInUnitPrice;
		this.outUnitPrice = goodsOutUnitPrice;
		this.image = goodsImage;
		this.repertory = repertory;
		this.supplierId = goodsSupplierId;
		this.supplierName = goodsSupplierName;
		this.supplierPhone = goodsSupplierPhone;
		this.supplierAddress = goodsSupplierAddress;
	}

	public String getRepertory() {
		return repertory;
	}

	public void setRepertory(String repertory) {
		this.repertory = repertory;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getInUnitPrice() {
		return inUnitPrice;
	}

	public void setInUnitPrice(String inUnitPrice) {
		this.inUnitPrice = inUnitPrice;
	}

	public String getOutUnitPrice() {
		return outUnitPrice;
	}

	public void setOutUnitPrice(String outUnitPrice) {
		this.outUnitPrice = outUnitPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

}
