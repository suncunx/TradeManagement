package com.trade.model;

public class OutBill {

	private String outBillId;

	private String goodsId;
	private String goodsName;
	private String goodsUnit;
	private String goodsOutUnitPrice;
	private String goodsImage;
	private String goodsCount;
	private String totalPrice;

	private String customerId;
	private String customerName;
	private String customerPhone;
	private String customerAddress;

	private String payStatus;

	private String deliverId;
	private String deliverMan;
	private String deliverPhone;
	/**
	 * 0:空闲 false
	 * 1:忙碌 true
	 */
	private String deliverManStatus;
	/**
	 * -1:未发货
	 * 0:配送中
	 * 1:交易完成
	 */
	private String deliverStatus;

	private String time;

	public OutBill(String outBillId, String goodsId, String goodsName,
			String goodsUnit, String goodsOutUnitPrice, String goodsImage,
			String goodsCount, String totalPrice, String customerId,
			String customerName, String customerPhone, String customerAddress,
			String payStatus, String deliverId, String deliverMan,
			String deliverPhone, String deliverManStatus, String deliverStatus,
			String time) {
		super();
		this.outBillId = outBillId;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsUnit = goodsUnit;
		this.goodsOutUnitPrice = goodsOutUnitPrice;
		this.goodsImage = goodsImage;
		this.goodsCount = goodsCount;
		this.totalPrice = totalPrice;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerAddress = customerAddress;
		this.payStatus = payStatus;
		this.deliverId = deliverId;
		this.deliverMan = deliverMan;
		this.deliverPhone = deliverPhone;
		this.deliverManStatus = deliverManStatus;
		this.deliverStatus = deliverStatus;
		this.time = time;
	}

	public String getOutBillId() {
		return outBillId;
	}

	public void setOutBillId(String outBillId) {
		this.outBillId = outBillId;
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

	public String getGoodsOutUnitPrice() {
		return goodsOutUnitPrice;
	}

	public void setGoodsOutUnitPrice(String goodsOutUnitPrice) {
		this.goodsOutUnitPrice = goodsOutUnitPrice;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getDeliverId() {
		return deliverId;
	}

	public void setDeliverId(String deliverId) {
		this.deliverId = deliverId;
	}

	public String getDeliverMan() {
		return deliverMan;
	}

	public void setDeliverMan(String deliverMan) {
		this.deliverMan = deliverMan;
	}

	public String getDeliverPhone() {
		return deliverPhone;
	}

	public void setDeliverPhone(String deliverPhone) {
		this.deliverPhone = deliverPhone;
	}

	public String getDeliverManStatus() {
		return deliverManStatus;
	}

	public void setDeliverManStatus(String deliverManStatus) {
		this.deliverManStatus = deliverManStatus;
	}

	public String getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
