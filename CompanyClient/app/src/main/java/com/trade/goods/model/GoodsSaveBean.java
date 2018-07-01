package com.trade.goods.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.trade.BR;

/**
 * Created by Stephen Sun on 2018/4/22 0022.
 * Email:suncunx@qq.com
 */

public class GoodsSaveBean extends BaseObservable{
    private String name;
    private String unit;
    private String inUnitPrice;
    private String outUnitPrice;
    private String image;
    private View.OnClickListener imageListener;
    private View.OnClickListener listener;

    public GoodsSaveBean(String name, String unit, String inUnitPrice, String outUnitPrice, String image, View.OnClickListener imageListener, View.OnClickListener listener) {
        this.name = name;
        this.unit = unit;
        this.inUnitPrice = inUnitPrice;
        this.outUnitPrice = outUnitPrice;
        this.image = image;
        this.imageListener = imageListener;
        this.listener = listener;
    }

    public View.OnClickListener getImageListener() {
        return imageListener;
    }

    public void setImageListener(View.OnClickListener imageListener) {
        this.imageListener = imageListener;
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
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
        notifyPropertyChanged(BR.unit);
    }
    @Bindable
    public String getInUnitPrice() {
        return inUnitPrice;
    }

    public void setInUnitPrice(String inUnitPrice) {
        this.inUnitPrice = inUnitPrice;
        notifyPropertyChanged(BR.inUnitPrice);
    }
    @Bindable
    public String getOutUnitPrice() {
        return outUnitPrice;
    }

    public void setOutUnitPrice(String outUnitPrice) {
        this.outUnitPrice = outUnitPrice;
        notifyPropertyChanged(BR.outUnitPrice);
    }

    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        notifyPropertyChanged(BR.image);
    }
    @Bindable
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
        notifyPropertyChanged(BR.listener);
    }
}
