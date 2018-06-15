package com.trade.servlet;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trade.model.Repertory;



public class RepertoryServlet extends BaseServlet {
	public void list(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Repertory> list = dbUtil.listRepertory(con, userId);
			HashMap<String, List> map = new HashMap<String, List>();
			map.put("repertorys", list);
			doResponse(response, getResultArray(200, "查询成功", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goodsCount(HttpServletRequest request, HttpServletResponse response) {
		try {
			String goodsId = request.getParameter("goodsId");
			List<Repertory> list = dbUtil.listRepertory(con, userId);
			String result = "";
			for (Repertory repertory : list) {
				if (repertory.getGoodsId().equals(goodsId)) {
					result = repertory.getGoodsCount();
					break;
				}
			}
			doResponse(response, getResult(200, "查询成功", result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
