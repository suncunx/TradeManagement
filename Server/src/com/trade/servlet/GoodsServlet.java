package com.trade.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.trade.model.Goods;
import com.trade.util.Util;

public class GoodsServlet extends BaseServlet {
	private String goodsId;
	private String name;
	private String unit;
	private String inUnitPrice;
	private String outUnitPrice;
	private String supplierId;

	public void save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		name = URLDecoder.decode(request.getHeader("name"), "utf-8");
		unit = URLDecoder.decode(request.getHeader("unit"), "utf-8");
		inUnitPrice = request.getHeader("inUnitPrice");
		outUnitPrice = request.getHeader("outUnitPrice");
		// image = request.getHeader("image");
		supplierId = request.getHeader("supplierId");

		System.out.println(userId + name + unit + inUnitPrice + outUnitPrice
				+ supplierId);
		// todo 同时获取表单和文件
		try {
			detectSaveFile(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}

	private void detectSaveFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig()
				.getServletContext();
		File repository = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();

			if (item.isFormField()) {
				processFormField(item);
			} else {
				processSaveUploadedFile(item, response);
			}
		}
	}

	private void processSaveUploadedFile(FileItem item, HttpServletResponse response)
			throws Exception {
		String fieldName = item.getFieldName();
		String fileName = item.getName();
		String contentType = item.getContentType();
		boolean isInMemory = item.isInMemory();
		long sizeInBytes = item.getSize();

		String time = Util.getDateSimple();
		System.out.println("fieldName = " + fieldName + " fileName = "
				+ fileName + " contentType = " + contentType);
		File uploadedFile = new File(
				"J:/MyEclipse 10/workspace/JavaEE/TradeManagement/WebRoot/image/"
						+ time + ".jpg");
		item.write(uploadedFile);
		dbUtil.saveGoods(con, userId, name, unit, inUnitPrice, outUnitPrice,
				time + ".jpg", supplierId);
		doResponse(response, getResult(200, "添加成功", "添加成功"));
	}

	private void processFormField(FileItem item) {
		String name = item.getFieldName();
		String value = item.getString();
		System.out.println("name = " + name + " value = " + value);
	}

	private void saveFile(HttpServletRequest request, String imageName) {
		// 利用request对象返回客户端来的输入流
		ServletInputStream sis;
		try {
			sis = request.getInputStream();
			System.out.println(request.getServletPath());
			String root = "J:/MyEclipse 10/workspace/JavaEE/TradeManagement/WebRoot/image/";
			File file = new File(root + imageName);
			OutputStream os = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(os);

			byte[] buf = new byte[1024];
			int length = 0;
			length = sis.readLine(buf, 0, buf.length);// 使用sis的读取数据的方法
			while (length != -1) {
				bos.write(buf, 0, length);
				length = sis.read(buf);
			}
			sis.close();
			bos.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		goodsId = request.getHeader("goodsId");
		name = URLDecoder.decode(request.getHeader("name"), "utf-8");
		unit = URLDecoder.decode(request.getHeader("unit"), "utf-8");
		inUnitPrice = request.getHeader("inUnitPrice");
		outUnitPrice = request.getHeader("outUnitPrice");
		String image = URLDecoder.decode(request.getHeader("image"), "utf-8");
		supplierId = request.getHeader("supplierId");
		System.out.println(image);
		System.out.println(goodsId + name + unit + inUnitPrice + outUnitPrice
				 + supplierId);

		try {
			if (image == null || image.equals("") || image.substring(0, 4).equals("http")) {
				dbUtil.updateGoods(con, userId, goodsId, name, unit, inUnitPrice, outUnitPrice,
						image == null ? image : image.substring(image.lastIndexOf("/")), supplierId);
				doResponse(response, getResult(200, "修改成功", "修改成功"));
			} else {
				detectUpdateFile(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}
	private void detectUpdateFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig()
				.getServletContext();
		File repository = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();

			if (item.isFormField()) {
				processFormField(item);
			} else {
				processUpdateUploadedFile(item, response);
			}
		}
	}

	private void processUpdateUploadedFile(FileItem item, HttpServletResponse response)
			throws Exception {
		String fieldName = item.getFieldName();
		String fileName = item.getName();
		String contentType = item.getContentType();

		String time = Util.getDateSimple();
		System.out.println("fieldName = " + fieldName + " fileName = "
				+ fileName + " contentType = " + contentType);
		File uploadedFile = new File(
				"J:/MyEclipse 10/workspace/JavaEE/TradeManagement/WebRoot/image/"
						+ time + ".jpg");
		item.write(uploadedFile);
		dbUtil.updateGoods(con, userId, goodsId, name, unit, inUnitPrice, outUnitPrice,
				time + ".jpg", supplierId);
		doResponse(response, getResult(200, "修改成功", "修改成功"));
	}
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		String goodsId = request.getParameter("goodsId");
		String supplierId = request.getParameter("supplierId");
		try {

			dbUtil.removeGoods(con, userId, supplierId, goodsId);
			doResponse(response, getResult(200, "删除成功", "删除成功"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}

	}

	public void list(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Goods> list = dbUtil.listGoods(con, userId);
			HashMap<String, List> map = new HashMap<String, List>();
			map.put("goodss", list);
			doResponse(response, getResultArray(200, "查询成功", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
