package com.trade.home.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

/**
 * Created by Stephen Sun on 2018/4/22 0022.
 * Email:suncunx@qq.com
 */

public class InBillSaveBean extends BaseObservable{
    private String goodsCount;
    private View.OnClickListener listener;

    public InBillSaveBean(String goodsCount, View.OnClickListener listener) {
        this.goodsCount = goodsCount;
        this.listener = listener;
    }

    @Bindable
    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }
}
