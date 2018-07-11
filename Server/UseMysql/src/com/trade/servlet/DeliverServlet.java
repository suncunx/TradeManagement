package com.trade.servlet;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trade.model.Deliver;
import com.trade.model.OutBillDetail;



public class DeliverServlet extends BaseServlet {
	public void list(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Deliver> list = dbUtil.listDeliverFree(con, userId);
			HashMap<String, List> map = new HashMap<String, List>();
			map.put("delivers", list);
			doResponse(response, getResultArray(200, "查询成功", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String userPhone = request.getParameter("phone");
		String userPwd = request.getParameter("password");
		System.out.println("------" + userPhone);
		System.out.println("------" + userPwd);
		try {
			String userId = dbUtil.loginDeliver(con, userPhone, userPwd);
			if (userId != null) {
				doResponse(response, getResult(200, "登录成功", userId));
			} else
				doResponse(response, getResult(201, "账号或密码错误", "账号或密码错误"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mission(HttpServletRequest request, HttpServletResponse response) {
		String deliverId = request.getParameter("deliverId");
		try {
			OutBillDetail outBill = dbUtil.missionDeliver(con, deliverId);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("outBill", outBill);
			if (outBill != null) {
				doResponse(response, getResultObj1(200, "存在任务", map));
			} else
				doResponse(response, getResult(201, "没有任务", "没有任务"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
