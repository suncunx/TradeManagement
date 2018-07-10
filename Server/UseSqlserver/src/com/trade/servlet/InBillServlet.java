package com.trade.servlet;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trade.model.InBill;
import com.trade.util.Constant;
import com.trade.util.Util;

public class InBillServlet extends BaseServlet {
	
	public void save(HttpServletRequest request, HttpServletResponse response) {
		String goodsId = request.getParameter("goodsId");
		String goodsCount = request.getParameter("goodsCount");
		String supplierId = request.getParameter("supplierId");
		String time = Util.getDate();

		System.out.println(userId + goodsId + goodsCount + supplierId + time);

		try {
			dbUtil.saveInBill(con, userId, goodsId, goodsCount, supplierId,
					time);
			doResponse(response, getResult(200, "添加成功", "添加成功"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		String inBillId = request.getParameter("inBillId");
		String goodsId = request.getParameter("goodsId");
		String goodsCount = request.getParameter("goodsCount");
		String supplierId = request.getParameter("supplierId");

		System.out.println(goodsId + inBillId + goodsId + goodsCount
				+ supplierId);

		try {
			dbUtil.updateInBill(con, userId, inBillId, goodsId, goodsCount,
					supplierId);
			doResponse(response, getResult(200, "修改成功", "修改成功"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}

	// public void remove(HttpServletRequest request, HttpServletResponse
	// response) {
	// String goodsId = request.getParameter("goodsId");
	// String supplierId = request.getParameter("supplierId");
	// try {
	//
	// dbUtil.removeInBill(con, userId, supplierId, goodsId);
	// doResponse(response, getResult(200, "删除成功", "删除成功"));
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// dbUtil.closeCon(con);
	// }
	//
	// }

	public void list(HttpServletRequest request, HttpServletResponse response) {
		String pageNo = request.getParameter("pageNo");
		if (pageNo == null || pageNo.equals("")) {
			pageNo = "1";
		}
		try {
			String rowTotal = dbUtil.pageInBill(con, userId); // 总行数
			int pageTotal = Integer.parseInt(rowTotal) / Integer.parseInt(Constant.PAGE_SIZE);
			List<InBill> list = dbUtil.listInBill(con, userId, Constant.PAGE_SIZE, pageNo);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", pageNo);
			map.put("pageSize", Constant.PAGE_SIZE);
			map.put("pageTotal", pageTotal);
			map.put("inBills", list);
			doResponse(response, getResultObj1(200, "查询成功", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
