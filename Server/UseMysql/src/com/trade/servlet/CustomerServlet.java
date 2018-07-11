package com.trade.servlet;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trade.model.Customer;



public class CustomerServlet extends BaseServlet {

	public void list(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Customer> list = dbUtil.listCustomer(con, userId);
			HashMap<String, List> map = new HashMap<String, List>();
			map.put("customers", list);
			doResponse(response, getResultArray(200, "查询成功", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String phone = request.getHeader("phone");
		String name = URLDecoder.decode(request.getHeader("name"), "utf-8");
		String address = URLDecoder.decode(request.getHeader("address"), "utf-8");

		System.out.println(userId + name + phone + address);

		try {
			dbUtil.saveCustomer(con, userId, phone, name, address);
			doResponse(response, getResult(200, "添加成功", "添加成功"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String customerId = request.getHeader("customerId");
		String phone = request.getHeader("phone");
		String name = URLDecoder.decode(request.getHeader("name"), "utf-8");
		String address = URLDecoder.decode(request.getHeader("address"), "utf-8");

		System.out.println(customerId + name + phone + address);
		try {
			dbUtil.updateCustomer(con, customerId, name, phone, address);
			doResponse(response, getResult(200, "修改成功", "修改成功"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}
	
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		String customerId = request.getParameter("customerId");

		try {
			dbUtil.removeCustomer(con, userId, customerId);
			doResponse(response, getResult(200, "删除成功", "删除成功"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}

}
