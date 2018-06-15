package com.trade.user.model;

/**
 * Created by Stephen Sun on 2017/7/10 0010.
 * Email:suncunx@qq.com
 */

public class UserItem {
    private int imageRes;
    private String title;

    public UserItem(int imageRes, String title) {
        this.imageRes = imageRes;
        this.title = title;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
