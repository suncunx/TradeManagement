package com.trade.other.focus.model;


import com.trade.other.focus.ui.NewsFragment;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public class FocusBean {

    private String id;
    private String title;
    private NewsFragment fragment;

    public FocusBean(String id, String title, NewsFragment fragment) {
        this.id = id;
        this.title = title;
        this.fragment = fragment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NewsFragment getFragment() {
        return fragment;
    }

    public void setFragment(NewsFragment fragment) {
        this.fragment = fragment;
    }
}
