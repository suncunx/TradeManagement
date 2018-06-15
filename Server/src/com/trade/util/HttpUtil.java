package com.trade.util;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {
	
	
	public static void invokeMethod(Object obj, HttpServletRequest request, HttpServletResponse response) {
		String servletPath = request.getServletPath();
//		System.out.println(servletPath);
		String methodName = servletPath.substring(1);
//		String className = obj.getClass().getName();
//		System.out.println(className);
//		int back = className.length() - 24;
		methodName = methodName.substring(0, methodName.indexOf("."));
//		System.out.println(methodName);
		Method method;
		try {
			method = obj.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(obj, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
