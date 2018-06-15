package com.trade.home.detail.outBill.save.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.trade.BR;

/**
 * Created by Stephen Sun on 2018/4/22 0022.
 * Email:suncunx@qq.com
 */

public class OutBillSaveBean extends BaseObservable{
    private String goodsCount;
    private String repertoryGoodsCount;
    private View.OnClickListener listener;

    public OutBillSaveBean(String goodsCount, String repertoryGoodsCount, View.OnClickListener listener) {
        this.goodsCount = goodsCount;
        this.repertoryGoodsCount = repertoryGoodsCount;
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

    @Bindable
    public String getRepertoryGoodsCount() {
        return repertoryGoodsCount;
    }

    public void setRepertoryGoodsCount(String repertoryGoodsCount) {
        this.repertoryGoodsCount = repertoryGoodsCount;
        notifyPropertyChanged(BR.repertoryGoodsCount);
    }
}
