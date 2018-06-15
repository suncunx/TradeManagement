package com.architecture.template.rv.fragment.adapter;

import android.support.annotation.LayoutRes;

import com.architecture.adapter.BaseAdapter;
import com.architecture.adapter.BaseViewHolder;
import com.architecture.template.rv.fragment.model.TRvFragmentBean;

/**
 * Created by Stephen Sun on 2017/7/19 0019.
 * Email:suncunx@qq.com
 */

public class TRvFragmentAdapter extends BaseAdapter<TRvFragmentBean> {
    public TRvFragmentAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TRvFragmentBean item) {
        //TODO 数据绑定
    }
}
