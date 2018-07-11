package com.trade.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends BaseServlet {

	public void loginDirect(HttpServletRequest request,
			HttpServletResponse response) {
		String userPhone = request.getParameter("phone");
		System.out.println("------" + userPhone);
		try {
			String userId = dbUtil.login(con, userPhone);
			if (userId != null) {
				doResponse(response, getResult(200, "登录成功", userId));
			} else
				doResponse(response, getResult(201, "账号不存在", "账号不存在"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void login(HttpServletRequest request,
			HttpServletResponse response) {
		String userPhone = request.getParameter("phone");
		String userPwd = request.getParameter("password");
		System.out.println("------" + userPhone);
		System.out.println("------" + userPwd);
		try {
			String userId = dbUtil.login(con, userPhone, userPwd);
			if (userId != null) {
				doResponse(response, getResult(200, "登录成功", userId));
			} else
				doResponse(response, getResult(201, "账号或密码错误", "账号或密码错误"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void register(HttpServletRequest request,
			HttpServletResponse response) {
		String userPhone = request.getParameter("phone");
		String userPwd = request.getParameter("password");
		System.out.println("------" + userPhone);
		System.out.println("------" + userPwd);
		try {
			String userId = dbUtil.register(con, userPhone, userPwd);
			if (userId != null) {
				if (userId.equals("")) {
					doResponse(response, getResult(201, "该用户已存在", "该用户已存在"));
				} else {
					doResponse(response, getResult(200, "登录成功", userId));
				}
			} else
				doResponse(response, getResult(201, "注册失败", "注册失败"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
