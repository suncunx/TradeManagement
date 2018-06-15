package com.trade.util;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.trade.json.BaseJson;

public class JsonUtil {

	private static String getResult1(int code,String msg,Object result){
		BaseJson json=new BaseJson();
		json.setCode(code);
		json.setMsg(msg);
		json.setResult(result);
		Gson gson=new Gson();
		return gson.toJson(json);
	}
	//获取result中为字符串键值对的结果
/**
 * result本身是一个对象
 * {"code":200,"msg":"登录成功","result":{"pwd":"111","name":"1001"}}
 * @param code
 * @param msg
 * @param map
 * @return
 */
	public static String getResult(int code,String msg,HashMap<String, String> map){
		return getResult1(code, msg, map);
	}
	
	//获取result对应字符串的结果
	/**
	 * {"code":200,"msg":"登录成功","result":"1001"}
	 * @param code
	 * @param msg
	 * @param value
	 */
	public static String getResult(int code,String msg,String value){
		return getResult1(code, msg, value);
	}

	/*
	 * 在result中生成指定名称的JSON对象，其实这里只需要设定后面的为对象就行
	 * {"code":200,"msg":"登录成功","result":{"userInfo":{"pwd":"111","name":"1001"}}}
	 */
	public static String getResultObj(int code,String msg,HashMap<String, HashMap<String, String>> map){
		return getResult1(code, msg, map);
	}
	public static String getResultObj1(int code,String msg,HashMap<String, Object> map){
		return getResult1(code, msg, map);
	}
	/**
	 * 指定result中jsonArray名称与jsonArray内容
	 * {"code":200,"msg":"登录成功","result":{"portList":[{"id":"1","type":"地上","status":"空闲"},{"id":"2","type":"地下","status":"占用"}]}}
	 * @param code
	 * @param msg
	 * @param map
	 * @return
	 */
	public static String getResultArray(int code,String msg,HashMap<String, List> map){
		return getResult1(code, msg, map);
	}
}
