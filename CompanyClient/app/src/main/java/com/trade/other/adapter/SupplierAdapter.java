package com.trade.other.adapter;

import android.support.annotation.LayoutRes;

import com.architecture.adapter.BaseAdapter;
import com.architecture.adapter.BaseViewHolder;
import com.trade.BR;
import com.trade.other.model.SupplierResultBean;

/**
 * Created by Stephen Sun on 2017/7/10 0010.
 * Email:suncunx@qq.com
 */

public class SupplierAdapter extends BaseAdapter<SupplierResultBean.ResultBean.SupplierBean> {

    public SupplierAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupplierResultBean.ResultBean.SupplierBean item) {
        helper.setVariable(BR.supplierBean, item);
    }
}
