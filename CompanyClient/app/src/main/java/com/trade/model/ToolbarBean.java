package com.trade.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.trade.BR;


/**
 * Created by Stephen Sun on 2017/7/10 0010.
 * Email:suncunx@qq.com
 */

public class ToolbarBean extends BaseObservable {

    private String title;
    private String city;
    private boolean home;

    private View.OnClickListener leftClickListener;
    private View.OnClickListener titleClickListener;
    private View.OnClickListener rightClickListener;

    public ToolbarBean(String title, String city, boolean home, View.OnClickListener leftClickListener, View.OnClickListener titleClickListener, View.OnClickListener rightClickListener) {
        this.title = title;
        this.city = city;
        this.home = home;
        this.leftClickListener = leftClickListener;
        this.titleClickListener = titleClickListener;
        this.rightClickListener = rightClickListener;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public boolean getHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
        notifyPropertyChanged(BR.home);
    }

    public View.OnClickListener getLeftClickListener() {
        return leftClickListener;
    }

    public void setLeftClickListener(View.OnClickListener leftClickListener) {
        this.leftClickListener = leftClickListener;
    }

    public View.OnClickListener getRightClickListener() {
        return rightClickListener;
    }

    public void setRightClickListener(View.OnClickListener rightClickListener) {
        this.rightClickListener = rightClickListener;
    }

    public View.OnClickListener getTitleClickListener() {
        return titleClickListener;
    }

    public void setTitleClickListener(View.OnClickListener titleClickListener) {
        this.titleClickListener = titleClickListener;
    }

    @Bindable
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        notifyPropertyChanged(BR.city);
    }
}
