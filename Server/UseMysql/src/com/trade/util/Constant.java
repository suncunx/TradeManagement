package com.trade.util;

public class Constant {

	public static final int CODE_SUCCESS = 200;
	public static final int CODE_FAIL = 400;

	public static final String USER_ID = "userId";
	public static final String PAGE_SIZE = "6";
	// 模拟器显示图片的服务器地址，不需要更改
	public static final String IMAGE_URL_LOCAL = "http://10.0.3.2:8080/TradeManagement/image/";
	// 真机显示图片的服务器地址，开发者需要将192.168.43.205配置为本机的ip
	public static final String IMAGE_URL = "http://192.168.43.205:8080/TradeManagement/image/";
	//图片在服务器的保存路径，需要开发者自行配置有效的文件夹路径，例如我想将图片保存在桌面的image文件夹中，在windows系统下应该将路径修改为 C:/Users/Administrator/Desktop/image/
	public static final String FILE_PATH = "F:/openSource/TradeManagement/Server/UseMysql/WebRoot/image/"; 

}
