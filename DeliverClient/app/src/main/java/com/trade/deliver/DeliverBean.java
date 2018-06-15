package com.trade.deliver;

import com.xuhao.android.libsocket.sdk.bean.ISendable;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class DeliverBean implements ISendable {
	private String content = "";
	private boolean fromUser;
	private String userId;
	private String deliverId;
	private String obId;
	private String status; // 0空闲 （交易完成）1忙碌（配送中）

	public DeliverBean(boolean fromUser, String userId, String deliverId, String obId, String status) {
		this.fromUser = fromUser;
		this.userId = userId;
		this.deliverId = deliverId;
		this.obId = obId;
		this.status = status;
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("fromUser", fromUser);
			jsonObject.put("userId", userId);
			jsonObject.put("deliverId", deliverId);
			jsonObject.put("obId", obId);
			jsonObject.put("status", status);
			content = jsonObject.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public boolean isFromUser() {
		return fromUser;
	}

	public void setFromUser(boolean fromUser) {
		this.fromUser = fromUser;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeliverId() {
		return deliverId;
	}

	public void setDeliverId(String deliverId) {
		this.deliverId = deliverId;
	}

	public String getObId() {
		return obId;
	}

	public void setObId(String obId) {
		this.obId = obId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public byte[] parse() {
		byte[] body = content.getBytes(Charset.defaultCharset());
		ByteBuffer bb = ByteBuffer.allocate(4 + body.length);
		bb.order(ByteOrder.BIG_ENDIAN);
		bb.putInt(body.length);
		bb.put(body);
		return bb.array();
	}

	@Override
	public String toString() {
		return "DeliverBean{" +
				"content='" + content + '\'' +
				", fromUser=" + fromUser +
				", userId='" + userId + '\'' +
				", deliverId='" + deliverId + '\'' +
				", obId='" + obId + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
