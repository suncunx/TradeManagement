package com.architecture.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.architecture.BR;

import java.util.List;

/**
 * Created by Stephen Sun on 2017/7/26 0026.
 * Email:suncunx@qq.com
 */

public class DropMenuBean extends BaseObservable {

    private List<String> tabTexts;
    private List<View> popupViews;
    private View contentView;

    private String tabText;

    private boolean close;

    public DropMenuBean() {
    }

    public DropMenuBean(List<String> tabTexts, List<View> popupViews, View contentView) {
        this.tabTexts = tabTexts;
        this.popupViews = popupViews;
        this.contentView = contentView;
    }

    public DropMenuBean(List<String> tabTexts, List<View> popupViews, View contentView, String tabText, boolean close) {
        this.tabTexts = tabTexts;
        this.popupViews = popupViews;
        this.contentView = contentView;
        this.tabText = tabText;
        this.close = close;
    }

    public List<String> getTabTexts() {
        return tabTexts;
    }

    public void setTabTexts(List<String> tabTexts) {
        this.tabTexts = tabTexts;
    }

    public List<View> getPopupViews() {
        return popupViews;
    }

    public void setPopupViews(List<View> popupViews) {
        this.popupViews = popupViews;
    }

    @Bindable
    public String getTabText() {
        return tabText;
    }

    public void setTabText(String tabText) {
        this.tabText = tabText;
        notifyPropertyChanged(BR.tabText);
    }

    public View getContentView() {
        return contentView;
    }

    public void setContentView(View contentView) {
        this.contentView = contentView;
    }

    @Bindable
    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
        notifyPropertyChanged(BR.close);
    }
}
