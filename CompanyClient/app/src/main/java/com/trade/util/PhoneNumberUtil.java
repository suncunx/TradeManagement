package com.trade.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberUtil {
    private static final String PhoneNumberStringPattern = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9])|(17[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";

    public static boolean isValidPhoneNumber(String number){
        if (!TextUtils.isEmpty(number)&&number.length()==13||!TextUtils.isEmpty(number)&&number.length()==11){
            return true;
        }
        Pattern p = Pattern.compile(PhoneNumberStringPattern);
        Matcher m = p.matcher(number);
        return m.matches();
    }
}