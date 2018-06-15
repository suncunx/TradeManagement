package com.trade.model;

public class Deliver {

	private String deliverId;
	private String deliverMan;
	private String deliverPhone;
	private String deliverManStatus;

	public Deliver(String deliverId, String deliverMan, String deliverPhone,
			String deliverManStatus) {
		super();
		this.deliverId = deliverId;
		this.deliverMan = deliverMan;
		this.deliverPhone = deliverPhone;
		this.deliverManStatus = deliverManStatus;
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

}
