package com.trade.servlet;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trade.model.FinanceAll;



public class FinanceServlet extends BaseServlet {

	public void list(HttpServletRequest request, HttpServletResponse response) {
		String year = request.getParameter("year");
		try {
			List<FinanceAll> list = dbUtil.listFinanceMonthDay(con, userId, year);
			HashMap<String, List> map = new HashMap<String, List>();
			map.put("finances", list);
			doResponse(response, getResultArray(200, "查询成功", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
