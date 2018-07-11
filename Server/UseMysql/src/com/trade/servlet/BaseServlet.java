package com.trade.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trade.util.Constant;
import com.trade.util.DbUtil;
import com.trade.util.HttpUtil;
import com.trade.util.JsonUtil;

public abstract class BaseServlet extends HttpServlet {
	protected String userId;

	protected DbUtil dbUtil;
	protected Connection con;

	private void handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		// Map<String, String> map = new HashMap<String, String>();
		// Enumeration headerNames = request.getHeaderNames();
		// while (headerNames.hasMoreElements()) {
		// String key = (String) headerNames.nextElement();
		// String value = request.getHeader(key);
		// System.out.println("key = " + key + " , value = " + value);
		// map.put(key, value);
		// }
		//
		// 为什么只能收到POST请求，不能收到GET请求
//		System.out.println("handleRequest");
		userId = request.getHeader(Constant.USER_ID);
		System.out.println("userId = " + userId);
		// System.out.println("headerUserId = " + headerUserId);
		if (userId == null || userId.trim().equals("")) {
			userId = request.getParameter(Constant.USER_ID); // 方便测试
		}
		dbUtil = new DbUtil();
		try {
			con = dbUtil.getCon();
			HttpUtil.invokeMethod(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		System.out.println("doGet");
		handleRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		System.out.println("doPost");
		handleRequest(req, resp);
	}

	/**
	 * 
	 * @param response
	 *            客户端请求时，对应的response对象
	 * @param respContent
	 *            返回给客户端的内容
	 * @throws IOException
	 */
	protected void doResponse(HttpServletResponse response, String respContent)
			throws IOException {
		PrintWriter out = response.getWriter();
		out.write(respContent);
	}

	// 获取result中为字符串键值对的结果
	/**
	 * result本身是一个对象
	 * {"code":200,"msg":"登录成功","result":{"pwd":"111","name":"1001"}}
	 * 
	 * @param code
	 * @param msg
	 * @param map
	 * @return
	 */
	public String getResult(int code, String msg, HashMap<String, String> map) {
		return translateBytes(JsonUtil.getResult(code, msg, map));
	}

	// 获取result对应字符串的结果
	/**
	 * {"code":200,"msg":"登录成功","result":"1001"}
	 * 
	 * @param code
	 * @param msg
	 * @param value
	 */
	public String getResult(int code, String msg, String value) {
		return translateBytes(JsonUtil.getResult(code, msg, value));
	}

	/*
	 * 在result中生成指定名称的JSON对象，其实这里只需要设定后面的为对象就行
	 * {"code":200,"msg":"登录成功","result":
	 * {"userInfo":{"pwd":"111","name":"1001"}}}
	 */
	public String getResultObj(int code, String msg,
			HashMap<String, HashMap<String, String>> map) {
		return translateBytes(JsonUtil.getResultObj(code, msg, map));
	}
	public String getResultObj1(int code, String msg,
			HashMap<String, Object> map) {
		return translateBytes(JsonUtil.getResultObj1(code, msg, map));
	}
	/**
	 * 指定result中jsonArray名称与jsonArray内容
	 * {"code":200,"msg":"登录成功","result":{"portList"
	 * :[{"id":"1","type":"地上","status"
	 * :"空闲"},{"id":"2","type":"地下","status":"占用"}]}}
	 * 
	 * @param code
	 * @param msg
	 * @param map
	 * @return
	 */
	public String getResultArray(int code, String msg, HashMap<String, List> map) {
		return translateBytes(JsonUtil.getResultArray(code, msg, map));
	}

	protected String translateBytes(String str) {
		try {
			// return new String(str.getBytes()); 1

			// return new String(str.getBytes(), "iso-8859-1");1important
			// return new String(str.getBytes(), "utf-8");1
			// return new String(str.getBytes(), "gbk");1
			// return new String(str.getBytes(), "gb2312");1

			// return new String(str.getBytes("gbk"), "utf-8"); 1
			// return new String(str.getBytes("iso-8859-1"), "utf-8");
			// return new String(str.getBytes("gbk"), "iso-8859-1");1important
			return new String(str.getBytes("utf-8"), "iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
