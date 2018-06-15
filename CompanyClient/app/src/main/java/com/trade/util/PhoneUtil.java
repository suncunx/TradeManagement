package com.trade.util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Stephen Sun on 2017/6/17 0017.
 * Email:suncunx@qq.com
 */

public class PhoneUtil {

    //调用系统的拨号服务实现电话拨打功能
    public static void callPhone(Context context, String phone) {
        //封装一个拨打电话的intent，并且将电话号码包装成一个Uri对象传入
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(android.net.Uri.parse("tel:" + phone));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);//内部类
    }
}
