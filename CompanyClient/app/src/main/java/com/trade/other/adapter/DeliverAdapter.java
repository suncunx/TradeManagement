package com.trade.other.adapter;

import android.support.annotation.LayoutRes;

import com.architecture.adapter.BaseAdapter;
import com.architecture.adapter.BaseViewHolder;
import com.trade.BR;
import com.trade.other.model.DeliverResultBean;

/**
 * Created by Stephen Sun on 2017/7/10 0010.
 * Email:suncunx@qq.com
 */

public class DeliverAdapter extends BaseAdapter<DeliverResultBean.ResultBean.DeliverBean> {

    public DeliverAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeliverResultBean.ResultBean.DeliverBean item) {
        helper.setVariable(BR.deliverBean, item);
    }
}
