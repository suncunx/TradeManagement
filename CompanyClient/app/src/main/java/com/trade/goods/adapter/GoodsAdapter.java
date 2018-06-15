package com.trade.goods.adapter;

import android.support.annotation.LayoutRes;

import com.architecture.adapter.BaseAdapter;
import com.architecture.adapter.BaseViewHolder;
import com.trade.BR;
import com.trade.model.GoodsResultBean;


/**
 * Created by Stephen Sun on 2017/7/10 0010.
 * Email:suncunx@qq.com
 */

public class GoodsAdapter extends BaseAdapter<GoodsResultBean.ResultBean.GoodsBean> {

    public GoodsAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsResultBean.ResultBean.GoodsBean item) {
        helper.setVariable(BR.goodsBean, item);
    }
}
