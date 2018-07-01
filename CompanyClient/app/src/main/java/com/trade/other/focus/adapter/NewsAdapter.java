package com.trade.other.focus.adapter;

import android.support.annotation.LayoutRes;

import com.architecture.adapter.BaseAdapter;
import com.architecture.adapter.BaseViewHolder;
import com.trade.BR;
import com.trade.other.focus.model.NewsResultBean;


/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * modify by AllenCoder
 */
public class NewsAdapter extends BaseAdapter<NewsResultBean.ArticleBean> {

    public static final int ONE_IMAGE = 1;

    public NewsAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsResultBean.ArticleBean item) {
        helper.setVariable(BR.newsItemBean, item);
    }

}
