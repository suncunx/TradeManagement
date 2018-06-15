package com.trade.servlet;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trade.model.Customer;
import com.trade.model.Deliver;
import com.trade.model.OutBill;
import com.trade.model.Repertory;
import com.trade.util.Constant;
import com.trade.util.Util;

public class OutBillServlet extends BaseServlet {

	public void list(HttpServletRequest request, HttpServletResponse response) {
		String pageNo = request.getParameter("pageNo");
		if (pageNo == null || pageNo.equals("")) {
			pageNo = "1";
		}
		try {
			String rowTotal = dbUtil.pageOutBill(con, userId); // 总行数
			int pageTotal = Integer.parseInt(rowTotal) / Integer.parseInt(Constant.PAGE_SIZE);
			List<OutBill> list = dbUtil.listOutBill(con, userId, pageNo);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("pageNo", pageNo);
			map.put("pageSize", Constant.PAGE_SIZE);
			map.put("pageTotal", pageTotal);
			map.put("outBills", list);
			doResponse(response, getResultObj1(200, "查询成功", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listRepertoryCustomerDeliver(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			System.out.println("outBill list method");
			List<Repertory> list1 = dbUtil.listRepertory(con, userId);
			List<Customer> list2 = dbUtil.listCustomer(con, userId);
			List<Deliver> list3 = dbUtil.listDeliverFree(con, userId);
			HashMap<String, List> map = new HashMap<String, List>();
			map.put("repertorys", list1);
			map.put("customers", list2);
			map.put("delivers", list3);
			doResponse(response, getResultArray(200, "查询成功", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save(HttpServletRequest request, HttpServletResponse response) {
		String goodsId = request.getParameter("goodsId");
		String goodsCount = request.getParameter("goodsCount");
		String customerId = request.getParameter("customerId");
		String payStatus = request.getParameter("payStatus");
		String deliverId = request.getParameter("deliverId");
		String deliverStatus = "";
		// if (deliverId == null || deliverId.trim().equals("")) {
		deliverId = "1";
		deliverStatus = "-1";
		// } else {
		// deliverStatus = "0";
		// }
		String time = Util.getDate();

		System.out.println(userId + goodsId + goodsCount + customerId
				+ payStatus + deliverId + deliverStatus + time);

		try {
			dbUtil.saveOutBill(con, userId, goodsId, goodsCount, customerId,
					payStatus, deliverId, deliverStatus, time);
			doResponse(response, getResult(200, "添加成功", "添加成功"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}

	// 进出货理论上不能修改商品id
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String outBillId = request.getParameter("outBillId");
		String goodsId = request.getParameter("goodsId");
		String goodsCount = request.getParameter("goodsCount");
		String customerId = request.getParameter("customerId");
		String payStatus = request.getParameter("payStatus");
		String deliverId = request.getParameter("deliverId");

		String deliverStatus = URLDecoder.decode(
				request.getParameter("deliverStatus"), "utf-8");

		System.out.println(goodsId + outBillId + goodsId + goodsCount
				+ customerId + payStatus + deliverId + deliverStatus);

		try {
			dbUtil.updateOutBill(con, userId, outBillId, goodsId, goodsCount,
					customerId, payStatus, deliverId, deliverStatus);
			doResponse(response, getResult(200, "修改成功", "修改成功"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}

	public void detail(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userId = request.getParameter("userId");
			String obId = request.getParameter("outBillId");
			OutBill outBill = dbUtil.detailOutBill(con, userId, obId);
//			for (OutBill bill : list) {
//				if (obId.equals(bill.getOutBillId())) {
//					outBill = bill;
//					break;
//				}
//			}
			if (outBill == null) {
				doResponse(response, getResult(201, "不存在该出货账单", "不存在该出货账单"));
			} else {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("outBill", outBill);
				doResponse(response, getResultObj1(200, "查询成功", map));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// public void remove(HttpServletRequest request, HttpServletResponse
	// response) {
	// String goodsId = request.getParameter("goodsId");
	// String supplierId = request.getParameter("supplierId");
	// try {
	//
	// dbUtil.removeOutBill(con, userId, supplierId, goodsId);
	// doResponse(response, getResult(200, "删除成功", "删除成功"));
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// dbUtil.closeCon(con);
	// }
	//
	// }

}
