package com.trade.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.trade.user.model.UserBean;

import skin.support.utils.SkinPreference;

/**
 * Created by Stephen Sun on 2016/7/14 0014.
 * Email:1243357168@qq.com
 */
public class PreferUtil {

    private static PreferUtil sInstance;
    private Context context;

    public static final String NAME = "appPref";

    public static final String NAME_JUDGE_LOGIN = "judgeLogin";
    public static final String NAME_USER_INFO = "userInfo";
    public static final String NAME_FONT_GLOBAL = "fontGlobal";
    public static final String NAME_SKIN = "skin";


    public static final String KEY_IS_LOGIN = "isLogin";

    public static final String KEY_PHONE = "phone";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_OUT_BILL = "outBill";

    public static final String KEY_USER_ID = "userId";
    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_USER_DESCRIBE = "userDescribe";
    public static final String KEY_USER_IMAGE = "userImage";

    public static final String KEY_FONT_GLOBAL = "fontGlobal";
    public static final String KEY_FONT_WEB = "fontWeb";
    public static final String KEY_SKIN = "skin";

    public static final String KEY_GOODS_URL = "goodsUrl";

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (SkinPreference.class) {
                if (sInstance == null) {
                    sInstance = new PreferUtil(context.getApplicationContext());
                }
            }
        }
    }

    public static PreferUtil getInstance() {
        return sInstance;
    }

    private PreferUtil(Context context) {
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

    public boolean isLogin() {
        boolean isLogin = getPrefer(NAME_JUDGE_LOGIN)
                .getBoolean(KEY_IS_LOGIN, false);
        return isLogin;
    }

    // 主页是否显示出货账单
    public boolean isOutBill() {
        boolean isLogin = getPrefer(NAME)
                .getBoolean(KEY_OUT_BILL, true);
        return isLogin;
    }
    public void setIsOutBill(boolean outBill) {
        SharedPreferences.Editor editor = getEditor(NAME);
        editor.putBoolean(KEY_OUT_BILL, outBill);
        editor.apply();
    }
    // 设置状态为已登录
    public void setHasLogin() {
        SharedPreferences.Editor editor = getEditor(NAME_JUDGE_LOGIN);
        editor.putBoolean(KEY_IS_LOGIN, true);
        editor.apply();
    }

    public void setLoginOut() {
        SharedPreferences.Editor editor = getEditor(NAME_JUDGE_LOGIN);
        editor.putBoolean(KEY_IS_LOGIN, false);
        editor.apply();

        setUserInfo("", "", "享受同步数据等完整功能");
    }

    public UserBean getUserInfo() {
        SharedPreferences preferences = getPrefer(NAME_USER_INFO);
        String userId = preferences.getString(KEY_USER_ID, "");
        String userName = preferences.getString(KEY_USER_NAME, UserBean.NAME);
        String userDescribe = preferences.getString(KEY_USER_DESCRIBE, UserBean.DESCRIBE);
        String userImage = preferences.getString(KEY_USER_IMAGE, "");
        return new UserBean(userId, userName, userDescribe, userImage);
    }

    public void setUserInfo(String userId, String userName, String userDescribe) {
        SharedPreferences.Editor editor = getEditor(NAME_USER_INFO);
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_USER_DESCRIBE, userDescribe);
        editor.apply();
    }

    public void setGlobalFont(int font) {
        SharedPreferences.Editor editor = getEditor(NAME_FONT_GLOBAL);
        editor.putInt(KEY_FONT_GLOBAL, font);
        editor.apply();
    }

    // 获取当前城市，如果用户未设置则默认为悉尼
    public int getGlobalFont() {
        SharedPreferences preferences = getPrefer(NAME_FONT_GLOBAL);
        return preferences.getInt(KEY_FONT_GLOBAL, Constants.FONT_NORMAL);
    }

    public void setSkin(String skin) {
        SharedPreferences.Editor editor = getEditor(NAME_SKIN);
        editor.putString(KEY_SKIN, skin);
        editor.apply();
    }

    // 获取当前城市，如果用户未设置则默认为悉尼
    public String getSkin() {
        SharedPreferences preferences = getPrefer(NAME_SKIN);
        return preferences.getString(KEY_SKIN, null);
    }

    public void setWebFont(int font) {
        SharedPreferences.Editor editor = getEditor(NAME_FONT_GLOBAL);
        editor.putInt(KEY_FONT_WEB, font);
        editor.apply();
    }

    // 获取当前城市，如果用户未设置则默认为悉尼
    public int getWebFont() {
        SharedPreferences preferences = getPrefer(NAME_FONT_GLOBAL);
        return preferences.getInt(KEY_FONT_WEB, Constants.FONT_NORMAL);
    }
}
