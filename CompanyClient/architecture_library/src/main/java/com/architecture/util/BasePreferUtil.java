package com.architecture.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Stephen Sun on 2016/7/14 0014.
 * Email:1243357168@qq.com
 */
public class BasePreferUtil {

    private static BasePreferUtil sInstance;
    private Context context;

    public static final String NAME_USER_INFO = "userInfo";

    public static final String KEY_TOKEN = "token";
    public static final String KEY_USER_ID = "userId";

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (BasePreferUtil.class) {
                if (sInstance == null) {
                    sInstance = new BasePreferUtil(context.getApplicationContext());
                }
            }
        }
    }

    public static BasePreferUtil getInstance() {
        return sInstance;
    }

    private BasePreferUtil(Context context) {
        this.context = context;
    }

    public SharedPreferences getPrefer(String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditor(String name) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.edit();
    }

    public String getToken() {
        return getPrefer(NAME_USER_INFO).getString(KEY_TOKEN, null);
    }

    // 设置状态为已登录
    public void setToken(String token) {
        SharedPreferences.Editor editor = getEditor(NAME_USER_INFO);
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }
    public String getUserId() {
        return getPrefer(NAME_USER_INFO).getString(KEY_USER_ID, null);
    }

    // 设置状态为已登录
    public void setUserId(String userId) {
        SharedPreferences.Editor editor = getEditor(NAME_USER_INFO);
        editor.putString(KEY_USER_ID, userId);
        editor.apply();
    }

}
