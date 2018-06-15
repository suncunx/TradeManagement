package com.trade.user.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.trade.BR;


/**
 * Created by Stephen Sun on 2017/7/12 0012.
 * Email:suncunx@qq.com
 * 用户的账户信息
 */

public class UserBean extends BaseObservable {
    public static final String NAME = "点击登录账号";
    public static final String DESCRIBE = "享受同步数据等完整功能";

    private String userId;
    private String name;
    private String describe;
    private String image;

    public UserBean(String userId, String name, String describe, String image) {
        this.userId = userId;
        this.name = name;
        this.describe = describe;
        this.image = image;
    }

    public static UserBean getBeforeLoginBean() {
        return new UserBean("", NAME, DESCRIBE, "");
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
        notifyPropertyChanged(BR.describe);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
