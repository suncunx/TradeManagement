package com.trade.user.login.util;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class LoginUtil {

    //检查电话号码是否正确
    public static boolean checkPhone(String phone) {
        if (phone.trim().length() != 11 || phone.charAt(0) != '1') {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (phone.charAt(i) > '9' || phone.charAt(i) < '0')
                return false;
        }
        return true;
    }

}
