package com.trade.home.adapter;

import android.support.annotation.LayoutRes;

import com.architecture.adapter.BaseAdapter;
import com.architecture.adapter.BaseViewHolder;
import com.trade.BR;
import com.trade.model.InBillResultBean;

/**
 * Created by Stephen Sun on 2018/4/19 0019.
 * Email:suncunx@qq.com
 */

public class InBillAdapter extends BaseAdapter<InBillResultBean.ResultBean.InBillBean> {

    public InBillAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, InBillResultBean.ResultBean.InBillBean item) {
        helper.setVariable(BR.inBillBean, item);
    }
}
