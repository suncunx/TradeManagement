package com.trade.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.trade.model.Customer;
import com.trade.model.Deliver;
import com.trade.model.Finance;
import com.trade.model.FinanceAll;
import com.trade.model.Goods;
import com.trade.model.InBill;
import com.trade.model.OutBill;
import com.trade.model.OutBillDetail;
import com.trade.model.Repertory;
import com.trade.model.Supplier;

public class DbUtil {

	// 数据库URL
	private String dbUrl = "jdbc:mysql://127.0.0.1:3306/trade?serverTimezone=UTC";
	// 数据库用户名
	private String dbUser = "root";
	// 数据库密码
	private String dbPwd = "";
	// JDBC名称
	private String jdbcName = "com.mysql.cj.jdbc.Driver";

	private static final int SUCCESS = 1;
	private static final int GOODS_EXIST = 0;

	// 获取数据库连接
	public Connection getCon() throws Exception {
		// 加载数据库驱动
		Class.forName(jdbcName).newInstance();
		// 获取数据库连接
		Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		return con;
	}

	// 关闭数据库连接
	public void closeCon(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 关闭结果集
	public void closeRs(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 用户登录
	public String login(Connection con, String userName, String userPwd) throws Exception {
		String sql = "select id from user where phone=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userName);
		pstmt.setString(2, userPwd);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			return rs.getString("id");
		else
			return null;
	}

	public String login(Connection con, String userPhone) throws Exception {
		String sql = "select * from user where phone=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userPhone);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			return rs.getString("id");
		else
			return null;
	}

	// 用户登录，注意此处的密码需要加密 todo 注册
	public String register(Connection con, String userName, String userPwd) throws Exception {
		String sql = "select id from user where phone=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userName);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			return "";
		else {
			saveUser(con, userName, userPwd);
			return login(con, userName, userPwd);
		}

	}

	private void saveUser(Connection con, String userName, String userPwd) throws Exception {
		String sql = "insert into user values(?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "");
		pstmt.setString(2, userName);
		pstmt.setString(3, userPwd);
		pstmt.setString(4, "");
		pstmt.execute();
	}

	/************************************ 供应商 ************************************************/
	// 查询供应商信息
	public List<Supplier> listSupplier(Connection con, String userId) throws Exception {

		List<Supplier> suppliers = new ArrayList<Supplier>();

		// 查询的sql语句
		String sql = "select supplier_id,name,phone,address from supplier,user_supplier_goods "
				+ "where user_id=? and supplier.id=user_supplier_goods.supplier_id and goods_id is null";
		// 获取PreparedStatement对象
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		// 执行查询
		ResultSet rs = pstmt.executeQuery();
		String supplierId = "";
		String supplierName = "";
		String supplierPhone = "";
		String supplierAddress = "";
		while (rs.next()) {
			supplierId = rs.getString("supplier_id");
			supplierName = rs.getString("name");
			supplierPhone = rs.getString("phone");
			supplierAddress = rs.getString("address");
			Supplier supplier = new Supplier(supplierId, supplierName, supplierPhone, supplierAddress);
			suppliers.add(supplier);
		}
		this.closeRs(rs);
		return suppliers;
	}

	// 保存供应商信息
	public void saveSupplier(Connection con, String userId, String phone, String name, String address)
			throws SQLException {

		String sql = "{call proSupplierSave(?,?,?,?,?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		cstmt.setString(2, phone);
		cstmt.setString(3, name);
		cstmt.setString(4, address);
		cstmt.registerOutParameter(5, Types.INTEGER);
		cstmt.execute();

		int result = cstmt.getInt(5);
		if (result == GOODS_EXIST) { // 已存在该供应商
			// TODO
		} else {

		}
	}

	// 修改供应商信息
	public void updateSupplier(Connection con, String supplierId, String supplierName, String supplierPhone,
			String supplierAddress) throws Exception {
		String sql = "update supplier set name=?,phone=?,address=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, supplierName);
		pstmt.setString(2, supplierPhone);
		pstmt.setString(3, supplierAddress);
		pstmt.setString(4, supplierId);
		pstmt.executeUpdate();
	}

	// 删除user对应的供应商
	public boolean removeSupplier(Connection con, String userId, String supplierId) throws Exception {
		String slq1 = "select * from user_supplier_goods where user_id = ? and supplier_id = ? and goods_id is not null";
		PreparedStatement pstmt1 = con.prepareStatement(slq1);
		pstmt1.setString(1, userId);
		pstmt1.setString(2, supplierId);
		// 执行查询
		ResultSet rs = pstmt1.executeQuery();
		if (rs.next()) {
			return false;
		} else {
			String sql = "delete user_supplier_goods where user_id=? and supplier_id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, supplierId);
			pstmt.executeUpdate();
			return true;
		}
	}

	/********************************************** 客户操作 *******************************************************/
	// 查询客户信息
	public List<Customer> listCustomer(Connection con, String userId) throws Exception {

		List<Customer> customers = new ArrayList<Customer>();

		// 查询的sql语句
		String sql = "select customer_id,name,phone,address from customer,user_customer "
				+ "where user_id=? and customer.id=user_customer.customer_id";
		// 获取PreparedStatement对象
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		// 执行查询
		ResultSet rs = pstmt.executeQuery();
		String customerId = "";
		String customerName = "";
		String customerPhone = "";
		String customerAddress = "";
		while (rs.next()) {
			customerId = rs.getString("customer_id");
			customerName = rs.getString("name");
			customerPhone = rs.getString("phone");
			customerAddress = rs.getString("address");
			Customer customer = new Customer(customerId, customerName, customerPhone, customerAddress);
			customers.add(customer);
		}
		this.closeRs(rs);
		return customers;
	}

	// 保存客户信息
	public void saveCustomer(Connection con, String userId, String phone, String name, String address)
			throws SQLException {

		String sql = "{call save_customer(?,?,?,?,?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		cstmt.setString(2, phone);
		cstmt.setString(3, name);
		cstmt.setString(4, address);
		cstmt.registerOutParameter(5, Types.INTEGER);
		cstmt.execute();

		int result = cstmt.getInt(5);
		if (result == GOODS_EXIST) { // 已存在该客户
			// TODO
		} else {

		}
	}

	// 修改客户信息
	public void updateCustomer(Connection con, String customerId, String customerName, String customerPhone,
			String customerAddress) throws Exception {
		String sql = "update customer set name=?,phone=?,address=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, customerName);
		pstmt.setString(2, customerPhone);
		pstmt.setString(3, customerAddress);
		pstmt.setString(4, customerId);
		pstmt.executeUpdate();
	}

	// 删除user对应的客户
	public void removeCustomer(Connection con, String userId, String customerId) throws Exception {
		String sql = "delete user_customer where user_id=? and customer_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, customerId);
		pstmt.executeUpdate();
	}

	/***************************************************** 商品 *************************************************/

	// 调用存储过程，对商品进行查询
	public List<Goods> listGoods(Connection con, String userId) throws Exception {
		List<Goods> goodss = new ArrayList<Goods>();

		String sql = "{call proGoodsQuery(?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		ResultSet rs = cstmt.executeQuery();

		String goodsId = "";
		String goodsName = "";
		String goodsUnit = "";
		String goodsInUnitPrice = "";
		String goodsOutUnitPrice = "";
		String goodsImage = "";
		String goodsRepertory = "";
		String goodsSupplierId = "";
		String goodsSupplierName = "";
		String goodsSupplierPhone = "";
		String goodsSupplierAddress = "";

		while (rs.next()) {
			goodsId = rs.getString("goodsId");
			goodsName = rs.getString("goodsName");
			goodsUnit = rs.getString("goodsUnit");
			goodsInUnitPrice = rs.getString("goodsInUnitPrice");
			goodsOutUnitPrice = rs.getString("goodsOutUnitPrice");
			goodsImage = Constant.IMAGE_URL + rs.getString("goodsImage");
			goodsRepertory = getRepertoryCount(con, goodsId, userId);
			goodsSupplierId = rs.getString("supplierId");
			goodsSupplierName = rs.getString("supplierName");
			goodsSupplierPhone = rs.getString("supplierPhone");
			goodsSupplierAddress = rs.getString("supplierAddress");
			Goods goods = new Goods(goodsId, goodsName, goodsUnit, goodsInUnitPrice, goodsOutUnitPrice, goodsImage,
					goodsRepertory, goodsSupplierId, goodsSupplierName, goodsSupplierPhone, goodsSupplierAddress);
			goodss.add(goods);
		}
		this.closeRs(rs);
		return goodss;
	}

	private String getRepertoryCount(Connection con, String goodsId, String userId) throws SQLException {
		// 查询的sql语句
		String sql = "select goods_count from repertory " // 去掉了goods级联
				+ "where user_id=? and goods_id=?";
		// 获取PreparedStatement对象
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, goodsId);
		// 执行查询
		ResultSet rs = pstmt.executeQuery();
		String repertoryGoodsCount = "";
		if (rs.next()) {
			repertoryGoodsCount = rs.getString("goods_count");
		}
		this.closeRs(rs);
		return repertoryGoodsCount == null || repertoryGoodsCount.equals("") ? "0" : repertoryGoodsCount;
	}

	public void saveGoods(Connection con, String userId, String goodsName, String goodsUnit, String goodsInUnitPrice,
			String goodsOutUnitPirce, String goodsImage, String supplierId) throws SQLException {

		String sql = "{call proGoodsSave(?,?,?,?,?,?,?,?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		cstmt.setString(2, goodsName);
		cstmt.setString(3, goodsUnit);
		cstmt.setString(4, goodsInUnitPrice);
		cstmt.setString(5, goodsOutUnitPirce);
		cstmt.setString(6, goodsImage);
		cstmt.setString(7, supplierId);
		cstmt.registerOutParameter(8, Types.INTEGER);
		cstmt.execute();

		int result = cstmt.getInt(8);
		if (result == GOODS_EXIST) {
			// TODO
		} else {

		}
	}

	public void updateGoods(Connection con, String userId, String goodsId, String goodsName, String goodsUnit,
			String goodsInUnitPrice, String goodsOutUnitPirce, String goodsImage, String supplierId)
			throws SQLException {

		String sql = "{call proGoodsUpdate(?,?,?,?,?,?,?,?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		cstmt.setString(2, goodsId);
		cstmt.setString(3, goodsName);
		cstmt.setString(4, goodsUnit);
		cstmt.setString(5, goodsInUnitPrice);
		cstmt.setString(6, goodsOutUnitPirce);
		cstmt.setString(7, goodsImage);
		cstmt.setString(8, supplierId);
		cstmt.execute();
		// TODO 更新商品信息成功
	}

	// 删除user对应的商品
	public void removeGoods(Connection con, String userId, String supplierId, String goodsId) throws Exception {
		String sql = "delete user_supplier_goods where user_id=? and supplier_id=? and goods_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, supplierId);
		pstmt.setString(3, goodsId);
		pstmt.executeUpdate();
	}

	/***************************************************** 进货账单 *************************************************/

	public String pageInBill(Connection con, String userId) throws Exception {
		String sql = "select count(*) from in_bill where user_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getString(1);
		}
		return "0";
	}

	// 调用存储过程，对商品进行查询
	public List<InBill> listInBill(Connection con, String userId, String pageSize, String page) throws Exception {
		List<InBill> inBills = new ArrayList<InBill>();

		String sql = "{call proInBillQuery(?,?,?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		cstmt.setString(2, pageSize);
		cstmt.setString(3, page);
		ResultSet rs = cstmt.executeQuery();

		String ibId = "";
		String goodsId = "";
		String goodsName = "";
		String goodsUnit = "";
		String goodsInUnitPrice = "";
		String goodsImage = "";
		String goodsCount = "";
		String ibTotalPrice = "";
		String goodsSupplierId = "";
		String goodsSupplierName = "";
		String goodsSupplierPhone = "";
		String goodsSupplierAddress = "";
		String ibTime = "";

		while (rs.next()) {
			ibId = rs.getString("ibId");
			goodsId = rs.getString("goodsId");
			goodsName = rs.getString("goodsName");
			goodsUnit = rs.getString("goodsUnit");
			goodsInUnitPrice = rs.getString("goodsInUnitPrice");
			goodsImage = Constant.IMAGE_URL + rs.getString("goodsImage");
			goodsCount = rs.getString("ibGoodsCount");
			ibTotalPrice = rs.getString("ibTotalPrice");
			goodsSupplierId = rs.getString("supplierId");
			goodsSupplierName = rs.getString("supplierName");
			goodsSupplierPhone = rs.getString("supplierPhone");
			goodsSupplierAddress = rs.getString("supplierAddress");
			ibTime = rs.getString("ibTime");
			InBill inBill = new InBill(ibId, goodsId, goodsName, goodsUnit, goodsImage, goodsInUnitPrice, goodsCount,
					ibTotalPrice, goodsSupplierId, goodsSupplierName, goodsSupplierPhone, goodsSupplierAddress, ibTime);

			inBills.add(inBill);
		}
		this.closeRs(rs);
		return inBills;
	}

	public void saveInBill(Connection con, String userId, String goodsId, String goodsCount, String supplierId,
			String time) throws SQLException {

		String sql = "{call proInBillSave(?,?,?,?,?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		cstmt.setString(2, goodsId);
		cstmt.setString(3, goodsCount);
		cstmt.setString(4, supplierId);
		cstmt.setString(5, time);
		cstmt.execute();
		// TODO success
	}

	public void updateInBill(Connection con, String userId, String ibId, String goodsId, String goodsCount,
			String supplierId) throws SQLException {

		String sql = "{call proInBillUpdate(?,?,?,?,?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		cstmt.setString(2, ibId);
		cstmt.setString(3, goodsId);
		cstmt.setString(4, goodsCount);
		cstmt.setString(5, supplierId);
		cstmt.execute();
		// TODO 更新进货账单信息成功
	}

	/***************************************************** 出货账单 *************************************************/

	// 出货账单总行数
	public String pageOutBill(Connection con, String userId) throws Exception {
		String sql = "select count(*) from out_bill where user_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getString(1);
		}
		return "0";
	}

	// 调用存储过程，对出货账单进行查询
	public List<OutBill> listOutBill(Connection con, String userId, String pageNo) throws Exception {
		List<OutBill> outBills = new ArrayList<OutBill>();
		String sql = "{call proOutBillQuery(?,?,?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		cstmt.setString(2, Constant.PAGE_SIZE);
		cstmt.setString(3, pageNo);
		ResultSet rs = cstmt.executeQuery();

		String obId = "";
		String goodsId = "";
		String goodsName = "";
		String goodsUnit = "";
		String goodsOutUnitPrice = "";
		String goodsImage = "";
		String goodsCount = "";
		String obTotalPrice = "";
		String customerId = "";
		String customerName = "";
		String customerPhone = "";
		String customerAddress = "";
		String obPayStatus = "";
		String deliverId = "";
		String deliverMan = "";
		String deliverPhone = "";
		String deliverManStatus = "";
		String deliverStatus = "";
		String obTime = "";

		while (rs.next()) {
			obId = rs.getString("obId");
			goodsId = rs.getString("goodsId");
			goodsName = rs.getString("goodsName");
			goodsUnit = rs.getString("goodsUnit");
			goodsOutUnitPrice = rs.getString("goodsOutUnitPrice");
			goodsImage = Constant.IMAGE_URL + rs.getString("goodsImage");
			goodsCount = rs.getString("obGoodsCount");
			obTotalPrice = rs.getString("obTotalPrice");
			customerId = rs.getString("customerId");
			customerName = rs.getString("customerName");
			customerPhone = rs.getString("customerPhone");
			customerAddress = rs.getString("customerAddress");
			obPayStatus = rs.getString("obPayStatus");
			deliverId = rs.getString("deliverId");
			deliverMan = rs.getString("deliverMan");
			deliverPhone = rs.getString("deliverPhone");
			deliverManStatus = rs.getString("deliverManStatus");

			deliverStatus = rs.getString("obDeliverStatus");
			if (deliverStatus.equals("-1")) {
				deliverStatus = "未发货";
			} else if (deliverStatus.equals("0")) {
				deliverStatus = "配送中";
			} else {
				deliverStatus = "交易完成";
			}
			obTime = rs.getString("obTime");
			OutBill outBill = new OutBill(obId, goodsId, goodsName, goodsUnit, goodsOutUnitPrice, goodsImage,
					goodsCount, obTotalPrice, customerId, customerName, customerPhone, customerAddress, obPayStatus,
					deliverId, deliverMan, deliverPhone, deliverManStatus, deliverStatus, obTime);

			outBills.add(outBill);
		}
		this.closeRs(rs);
		return outBills;
	}

	public OutBill detailOutBill(Connection con, String userId, String obId) throws Exception {
		String sql = "select out_bill.id as out_bill_id,goods.id as goods_id,goods.name as goods_name,unit,out_unit_price,image,goods_count,"
				+ "out_unit_price*goods_count as total_price,customer.id as customer_id,customer.name as customer_name,customer.phone as customer_phone,address,"
				+ "pay_status,deliverer.id as deliverer_id,deliverer.name as deliverer_name,deliverer.phone as deliverer_phone,status,deliver_status,time from out_bill,"
				+ "goods,customer,deliverer where out_bill.user_id = ? and out_bill.id = ?";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		cstmt.setString(2, obId);
		ResultSet rs = cstmt.executeQuery();

		// String obId = "";
		String goodsId = "";
		String goodsName = "";
		String goodsUnit = "";
		String goodsOutUnitPrice = "";
		String goodsImage = "";
		String goodsCount = "";
		String obTotalPrice = "";
		String customerId = "";
		String customerName = "";
		String customerPhone = "";
		String customerAddress = "";
		String obPayStatus = "";
		String deliverId = "";
		String deliverMan = "";
		String deliverPhone = "";
		String deliverManStatus = "";
		String deliverStatus = "";
		String obTime = "";

		if (rs.next()) {
			obId = rs.getString("out_bill_id");
			goodsId = rs.getString("goods_id");
			goodsName = rs.getString("goods_name");
			goodsUnit = rs.getString("unit");
			goodsOutUnitPrice = rs.getString("out_unit_price");
			goodsImage = Constant.IMAGE_URL_LOCAL + rs.getString("image");
			goodsCount = rs.getString("goods_count");
			obTotalPrice = rs.getString("total_price");
			customerId = rs.getString("customer_id");
			customerName = rs.getString("customer_name");
			customerPhone = rs.getString("customer_phone");
			customerAddress = rs.getString("address");
			obPayStatus = rs.getString("pay_status");
			deliverId = rs.getString("deliverer_id");
			deliverMan = rs.getString("deliverer_name");
			deliverPhone = rs.getString("deliverer_phone");
			deliverManStatus = rs.getString("status");

			deliverStatus = rs.getString("deliver_status");
			if (deliverStatus.equals("-1")) {
				deliverStatus = "未发货";
			} else if (deliverStatus.equals("0")) {
				deliverStatus = "配送中";
			} else {
				deliverStatus = "交易完成";
			}
			obTime = rs.getString("time");
			OutBill outBill = new OutBill(obId, goodsId, goodsName, goodsUnit, goodsOutUnitPrice, goodsImage,
					goodsCount, obTotalPrice, customerId, customerName, customerPhone, customerAddress, obPayStatus,
					deliverId, deliverMan, deliverPhone, deliverManStatus, deliverStatus, obTime);

			// outBills.add(outBill);
			return outBill;
		}
		this.closeRs(rs);
		return null;
	}

	public void saveOutBill(Connection con, String userId, String goodsId, String goodsCount, String customerId,
			String payStatus, String deliverId, String deliverStatus, String time) throws SQLException {

		String sql = "{call proOutBillSave(?,?,?,?,?,?,?,?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		cstmt.setString(2, goodsId);
		cstmt.setString(3, goodsCount);
		cstmt.setString(4, customerId);
		cstmt.setString(5, payStatus);
		cstmt.setString(6, deliverId);
		cstmt.setString(7, deliverStatus);
		cstmt.setString(8, time);
		cstmt.execute();
		// TODO success
	}

	public void updateOutBill(Connection con, String userId, String obId, String goodsId, String goodsCount,
			String customerId, String payStatus, String deliverId, String deliverStatus) throws SQLException {

		String sql = "{call proOutBillUpdate(?,?,?,?,?,?,?,?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		cstmt.setString(2, obId);
		cstmt.setString(3, goodsId);
		cstmt.setString(4, goodsCount);
		cstmt.setString(5, customerId);
		cstmt.setString(6, payStatus);
		cstmt.setString(7, deliverId);
		cstmt.setString(8, deliverStatus);
		cstmt.execute();
		// TODO 更新进货账单信息成功
	}

	// TODO 库存接口
	/*********************************************** 库存 ********************************/
	// 查询库存信息
	public List<Repertory> listRepertory(Connection con, String userId) throws Exception {

		List<Repertory> Repertories = new ArrayList<Repertory>();

		// 查询的sql语句
		String sql = "select repertory.id as repertory_id,goods_id,name,unit,goods_count,image from repertory,goods "
				+ "where user_id=? and goods_id=goods.id";
		// 获取PreparedStatement对象
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		// 执行查询
		ResultSet rs = pstmt.executeQuery();
		String repertoryId = "";
		String repertoryGoodsId = "";
		String repertoryGoodsName = "";
		String repertoryGoodsUnit = "";
		String repertoryGoodsCount = "";
		String repertoryGoodsImage = "";
		while (rs.next()) {
			repertoryId = rs.getString("repertory_id");
			repertoryGoodsId = rs.getString("goods_id");
			repertoryGoodsName = rs.getString("name");
			repertoryGoodsUnit = rs.getString("unit");
			repertoryGoodsCount = rs.getString("goods_count");
			repertoryGoodsImage = Constant.IMAGE_URL + rs.getString("image");
			Repertory repertory = new Repertory(repertoryId, repertoryGoodsId, repertoryGoodsName, repertoryGoodsUnit,
					repertoryGoodsCount, repertoryGoodsImage);
			Repertories.add(repertory);
		}
		this.closeRs(rs);
		return Repertories;
	}

	/*********************************************** 送货员 ********************************/

	public String loginDeliver(Connection con, String userName, String userPwd) throws Exception {
		String sql = "select id from deliverer where phone=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userName);
		pstmt.setString(2, userPwd);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			return rs.getString("id");
		else
			return null;
	}

	public OutBillDetail missionDeliver(Connection con, String deliverId) throws Exception {

		String sql = "select obId,goodsId,goodsName,goodsUnit,goodsOutUnitPrice,goodsImage,obGoodsCount,"
				+ "goodsOutUnitPrice*obGoodsCount obTotalPrice,customerId,customerName,customerPhone,"
				+ "customerAddress,obPayStatus,deliverId,deliverMan,deliverPhone,deliverManStatus,obDeliverStatus,obUserId,obTime from"
				+ " outBill,goods,customer,deliver where obDeliverStatus = 0 and obDeliverId = ? and obGoodsId = goodsId"
				+ " and obCustomerId = customerId and obDeliverId = deliverId";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, deliverId);
		ResultSet rs = pstmt.executeQuery();

		String obId = "";
		String goodsId = "";
		String goodsName = "";
		String goodsUnit = "";
		String goodsOutUnitPrice = "";
		String goodsImage = "";
		String goodsCount = "";
		String obTotalPrice = "";
		String customerId = "";
		String customerName = "";
		String customerPhone = "";
		String customerAddress = "";
		String obPayStatus = "";
		String deliverMan = "";
		String deliverPhone = "";
		String deliverManStatus = "";
		String deliverStatus = "";
		String userId = "";
		String obTime = "";

		if (rs.next()) {
			obId = rs.getString("obId");
			goodsId = rs.getString("goodsId");
			goodsName = rs.getString("goodsName");
			goodsUnit = rs.getString("goodsUnit");
			goodsOutUnitPrice = rs.getString("goodsOutUnitPrice");
			goodsImage = Constant.IMAGE_URL_LOCAL + rs.getString("goodsImage");
			goodsCount = rs.getString("obGoodsCount");
			obTotalPrice = rs.getString("obTotalPrice");
			customerId = rs.getString("customerId");
			customerName = rs.getString("customerName");
			customerPhone = rs.getString("customerPhone");
			customerAddress = rs.getString("customerAddress");
			obPayStatus = rs.getString("obPayStatus");
			deliverId = rs.getString("deliverId");
			deliverMan = rs.getString("deliverMan");
			deliverPhone = rs.getString("deliverPhone");
			deliverManStatus = rs.getString("deliverManStatus");

			deliverStatus = rs.getString("obDeliverStatus");
			if (deliverStatus.equals("-1")) {
				deliverStatus = "未发货";
			} else if (deliverStatus.equals("0")) {
				deliverStatus = "配送中";
			} else {
				deliverStatus = "交易完成";
			}
			userId = rs.getString("obUserId");
			obTime = rs.getString("obTime");
			OutBillDetail outBill = new OutBillDetail(obId, goodsId, goodsName, goodsUnit, goodsOutUnitPrice,
					goodsImage, goodsCount, obTotalPrice, customerId, customerName, customerPhone, customerAddress,
					obPayStatus, deliverId, deliverMan, deliverPhone, deliverManStatus, deliverStatus, userId, obTime);
			return outBill;
		} else {
			return null;
		}
	}

	// 查询送货员信息
	public List<Deliver> listDeliver(Connection con, String userId) throws Exception {

		List<Deliver> delivers = new ArrayList<Deliver>();

		// 查询的sql语句
		String sql = "select id,name,phone,status from deliverer " + "where user_id=?";
		// 获取PreparedStatement对象
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		// 执行查询
		ResultSet rs = pstmt.executeQuery();
		String deliverId = "";
		String deliverMan = "";
		String deliverPhone = "";
		String deliverManStatus = "";
		while (rs.next()) {
			deliverId = rs.getString("id");
			deliverMan = rs.getString("name");
			deliverPhone = rs.getString("phone");
			deliverManStatus = rs.getString("status");
			Deliver deliver = new Deliver(deliverId, deliverMan, deliverPhone, deliverManStatus);
			delivers.add(deliver);
		}
		this.closeRs(rs);
		return delivers;
	}

	// 查询空闲送货员信息
	public List<Deliver> listDeliverFree(Connection con, String userId) throws Exception {

		List<Deliver> delivers = new ArrayList<Deliver>();

		// 查询的sql语句
		String sql = "select id,name,phone,status from deliverer "
				+ "where user_id=? and status = 0";
		// 获取PreparedStatement对象
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		// 执行查询
		ResultSet rs = pstmt.executeQuery();
		String deliverId = "";
		String deliverMan = "";
		String deliverPhone = "";
		String deliverManStatus = "";
		while (rs.next()) {
			deliverId = rs.getString("id");
			deliverMan = rs.getString("name");
			deliverPhone = rs.getString("phone");
			deliverManStatus = rs.getString("status");
			Deliver deliver = new Deliver(deliverId, deliverMan, deliverPhone, deliverManStatus);
			delivers.add(deliver);
		}
		this.closeRs(rs);
		return delivers;
	}

	/************************************** 财务报表 *******************************************/
	public List<FinanceAll> listFinanceMonthDay(Connection con, String userId, String year) throws SQLException {
		List<FinanceAll> finances = new ArrayList<FinanceAll>();

		float inBillPrice = 0;
		float outBillPrice = 0;
		float profit = 0;
		for (int i = 1; i <= 12; i++) {
			Map<String, String> hm = Util.getTimeInterval(year, "" + i);
			String startTime = hm.get(Util.KEY_START_TIME);
			String endTime = hm.get(Util.KEY_END_TIME);
			inBillPrice = queryInBillPrice(con, userId, startTime, endTime);
			outBillPrice = queryOutBillPrice(con, userId, startTime, endTime);
			profit = outBillPrice - inBillPrice;
			FinanceAll financeAll = new FinanceAll(inBillPrice, outBillPrice, profit);

			List<Finance> list = listFinanceDay(con, userId, year, i + "");
			financeAll.setFinances(list);
			finances.add(financeAll);
		}
		return finances;
	}

	public List<Finance> listFinanceMonth(Connection con, String userId, String year) throws SQLException {
		List<Finance> finances = new ArrayList<Finance>();

		float inBillPrice = 0;
		float outBillPrice = 0;
		float profit = 0;
		for (int i = 1; i <= 12; i++) {
			Map<String, String> hm = Util.getTimeInterval(year, "" + i);
			String startTime = hm.get(Util.KEY_START_TIME);
			String endTime = hm.get(Util.KEY_END_TIME);
			inBillPrice = queryInBillPrice(con, userId, startTime, endTime);
			outBillPrice = queryOutBillPrice(con, userId, startTime, endTime);
			profit = outBillPrice - inBillPrice;
			Finance finance = new Finance(inBillPrice, outBillPrice, profit);
			finances.add(finance);
		}
		return finances;
	}

	// 按月获取每天的财务报表
	public List<Finance> listFinanceDay(Connection con, String userId, String year, String month) throws SQLException {
		List<Finance> finances = new ArrayList<Finance>();
		Map<String, String> interval = Util.getTimeInterval(year, month);

		int days = Util.getLastDayInMonth(year, month);
		float inBillPrice = 0;
		float outBillPrice = 0;
		float profit = 0;
		for (int i = 1; i <= days; i++) {
			Map<String, String> hm = Util.getTimeInterval(year, month, "" + i);
			String startTime = hm.get(Util.KEY_START_TIME);
			String endTime = hm.get(Util.KEY_END_TIME);
			inBillPrice = queryInBillPrice(con, userId, startTime, endTime);
			outBillPrice = queryOutBillPrice(con, userId, startTime, endTime);
			profit = outBillPrice - inBillPrice;
			Finance finance = new Finance(inBillPrice, outBillPrice, profit);
			finances.add(finance);
		}
		return finances;
	}

	// 时间区间内出货总价格
	private float queryOutBillPrice(Connection con, String userId, String startTime, String endTime)
			throws SQLException {
		System.out.println("out start = " + startTime + "   end = " + endTime);
		float price = 0;
		// 查询的sql语句
		String sql = "select sum(out_unit_price*goods_count) as total_price from out_bill,goods where user_id = ? and "
				+ "time > ? and time < ? and goods.id = goods_id";
		// 获取PreparedStatement对象
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, startTime);
		pstmt.setString(3, endTime);
		// 执行查询
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			price = rs.getFloat("total_price");
			System.out.println("-----out-----" + price + "------out------");
		}
		return price;
	}

	// 时间区间内进货总价格
	private float queryInBillPrice(Connection con, String userId, String startTime, String endTime)
			throws SQLException {
		System.out.println("in start = " + startTime + "   end = " + endTime);
		float price = 0;
		// 查询的sql语句
		String sql = "select sum(in_unit_price*goods_count) as total_price from in_bill,goods where user_id = ? and "
				+ "time > ? and time < ? and goods.id = goods_id";
		// 获取PreparedStatement对象
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, startTime);
		pstmt.setString(3, endTime);
		// 执行查询
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			price = rs.getFloat("total_price");
			System.out.println("----in------" + price + "-------in-----");
		}
		return price;
	}

	// 交易完成
	public void updateDeliverStatus(Connection con, String userId, String obId, String deliverId) throws Exception {
		String sql = "{call proDeliverUpdate(?,?,?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setString(1, userId);
		cstmt.setString(2, obId);
		cstmt.setString(3, deliverId);
		cstmt.execute();
	}

}
