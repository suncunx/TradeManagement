package com.trade.model;

public class Repertory {

	private String repertoryId;
	private String goodsId;
	private String goodsName;
	private String goodsUnit;
	private String goodsCount;
	private String goodsImage;

	public Repertory(String repertoryId, String goodsId,
			String goodsName, String goodsUnit,
			String goodsCount, String goodsImage) {
		super();
		this.repertoryId = repertoryId;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsUnit = goodsUnit;
		this.goodsCount = goodsCount;
		this.goodsImage = goodsImage;
	}

	public String getRepertoryId() {
		return repertoryId;
	}

	public void setRepertoryId(String repertoryId) {
		this.repertoryId = repertoryId;
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

	public String getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

}
