package com.architecture.template.rv.toolbar.adapter;

import android.support.annotation.LayoutRes;

import com.architecture.adapter.BaseAdapter;
import com.architecture.adapter.BaseViewHolder;
import com.architecture.template.rv.toolbar.model.TRvToolbarBean;

/**
 * Created by Stephen Sun on 2017/7/27 0027.
 * Email:suncunx@qq.com
 */

public class TRvToolbarAdapter extends BaseAdapter<TRvToolbarBean> {

    public TRvToolbarAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TRvToolbarBean item) {
        //TODO 设置数据
    }
}
