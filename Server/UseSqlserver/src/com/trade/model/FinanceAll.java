package com.trade.model;

import java.util.List;

public class FinanceAll {

	private float inBillPrice;
	private float outBillPrice;
	private float profit;
	private List<Finance> finances;

	public FinanceAll(float inBillPrice, float outBillPrice, float profit) {
		super();
		this.inBillPrice = inBillPrice;
		this.outBillPrice = outBillPrice;
		this.profit = profit;
	}

	public float getInBillPrice() {
		return inBillPrice;
	}

	public void setInBillPrice(float inBillPrice) {
		this.inBillPrice = inBillPrice;
	}

	public float getOutBillPrice() {
		return outBillPrice;
	}

	public void setOutBillPrice(float outBillPrice) {
		this.outBillPrice = outBillPrice;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public List<Finance> getFinances() {
		return finances;
	}

	public void setFinances(List<Finance> finances) {
		this.finances = finances;
	}

}
