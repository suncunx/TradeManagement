package com.trade.deliver;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Stephen Sun on 2016/7/14 0014.
 * Email:1243357168@qq.com
 */
public class PreferUtil {

    private static PreferUtil sInstance;
    private Context context;

    public static final String NAME_JUDGE_LOGIN = "judgeLogin";
    public static final String NAME_USER_INFO = "userInfo";


    public static final String KEY_IS_LOGIN = "isLogin";

    public static final String KEY_PHONE = "phone";
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_DELIVER_ID = "deliverId";
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_USER_DESCRIBE = "userDescribe";

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (PreferUtil.class) {
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

    private SharedPreferences getPrefer(String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor(String name) {
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.edit();
    }
    public void setDeliverBean(DeliverBean bean) {
        setDeliverId(bean.getDeliverId());
        setUserId(bean.getUserId());
        setOutBillId(bean.getObId());
    }
    public String getDeliverId() {
        return getPrefer(NAME_USER_INFO).getString(KEY_DELIVER_ID, null);
    }

    public void setDeliverId(String deliverId) {
        SharedPreferences.Editor editor = getEditor(NAME_USER_INFO);
        editor.putString(KEY_DELIVER_ID, deliverId);
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

    public String getOutBillId() {
        return getPrefer(NAME_USER_INFO).getString("outBillId", null);
    }

    // 设置状态为已登录
    public void setOutBillId(String outBillId) {
        SharedPreferences.Editor editor = getEditor(NAME_USER_INFO);
        editor.putString("outBillId", outBillId);
        editor.apply();
    }

    public boolean isLogin() {
        boolean isLogin = getPrefer(NAME_JUDGE_LOGIN)
                .getBoolean(KEY_IS_LOGIN, false);
        return isLogin;
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

    public void setUserInfo(String deliverId, String userName, String userDescribe) {
        SharedPreferences.Editor editor = getEditor(NAME_USER_INFO);
        editor.putString(KEY_DELIVER_ID, deliverId);
        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_USER_DESCRIBE, userDescribe);
        editor.apply();
    }

}
