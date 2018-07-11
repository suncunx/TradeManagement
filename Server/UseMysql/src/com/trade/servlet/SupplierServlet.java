package com.trade.servlet;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trade.model.Supplier;

public class SupplierServlet extends BaseServlet {
	
	public void save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String phone = request.getHeader("phone");
		String name = URLDecoder.decode(request.getHeader("name"), "utf-8");
		String address = URLDecoder.decode(request.getHeader("address"), "utf-8");

		System.out.println(userId + name  + phone+ address);

		try {
			dbUtil.saveSupplier(con, userId, phone, name, address);
			doResponse(response, getResult(200, "添加成功", "添加成功"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String supplierId = request.getHeader("supplierId");
		String phone = request.getHeader("phone");
		String name = URLDecoder.decode(request.getHeader("name"), "utf-8");
		String address = URLDecoder.decode(request.getHeader("address"), "utf-8");

		System.out.println(supplierId + name + phone + address);

		try {
			dbUtil.updateSupplier(con, supplierId, name, phone, address);
			doResponse(response, getResult(200, "修改成功", "修改成功"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}
	
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		String supplierId = request.getParameter("supplierId");

		try {
			boolean success = dbUtil.removeSupplier(con, userId, supplierId);
			if (success) {
				doResponse(response, getResult(200, "删除成功", "删除成功"));
			} else {
				doResponse(response, getResult(201, "删除失败", "该供应商存在商品"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}

	public void list(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Supplier> list = dbUtil.listSupplier(con, userId);
			HashMap<String, List> map = new HashMap<String, List>();
			map.put("suppliers", list);
			doResponse(response, getResultArray(200, "查询成功", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
