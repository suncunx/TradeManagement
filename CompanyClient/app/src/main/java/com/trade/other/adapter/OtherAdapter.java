package com.trade.other.adapter;

import android.support.annotation.LayoutRes;

import com.architecture.adapter.BaseAdapter;
import com.architecture.adapter.BaseViewHolder;
import com.trade.BR;
import com.trade.other.model.OtherItem;

/**
 * Created by Stephen Sun on 2017/7/10 0010.
 * Email:suncunx@qq.com
 */

public class OtherAdapter extends BaseAdapter<OtherItem> {

    public OtherAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OtherItem item) {
        helper.setVariable(BR.otherItem, item);
    }
}
