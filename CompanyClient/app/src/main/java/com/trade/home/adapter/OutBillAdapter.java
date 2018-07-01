package com.trade.home.adapter;

import android.support.annotation.LayoutRes;

import com.architecture.adapter.BaseAdapter;
import com.architecture.adapter.BaseViewHolder;
import com.trade.BR;
import com.trade.home.model.OutBillResultBean;

/**
 * Created by Stephen Sun on 2018/4/19 0019.
 * Email:suncunx@qq.com
 */

public class OutBillAdapter extends BaseAdapter<OutBillResultBean.ResultBean.OutBillBean> {

    public OutBillAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OutBillResultBean.ResultBean.OutBillBean item) {
//        float price = Float.parseFloat(item.getTotalPrice());
//        float shouldPrice = Float.parseFloat(item.getGoodsCount()) * Float.parseFloat(item.getGoodsOutUnitPrice());
//        LogUtils.i("shouldPrice = " + shouldPrice + "   price = " + price );
//        if (price != shouldPrice) {
//            item.setTotalPrice(price + "");
//        }
        helper.setVariable(BR.outBillBean, item);
    }
}
