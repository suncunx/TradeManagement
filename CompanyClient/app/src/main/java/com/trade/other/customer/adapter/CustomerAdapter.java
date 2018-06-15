package com.trade.other.customer.adapter;

import android.support.annotation.LayoutRes;

import com.architecture.adapter.BaseAdapter;
import com.architecture.adapter.BaseViewHolder;
import com.trade.BR;
import com.trade.model.CustomerResultBean;

/**
 * Created by Stephen Sun on 2017/7/10 0010.
 * Email:suncunx@qq.com
 */

public class CustomerAdapter extends BaseAdapter<CustomerResultBean.ResultBean.CustomerBean> {

    public CustomerAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerResultBean.ResultBean.CustomerBean item) {
        helper.setVariable(BR.customerBean, item);
    }
}
