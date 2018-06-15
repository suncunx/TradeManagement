package com.trade.other.focus.news.mvp;

import android.content.Context;

import com.architecture.mvp.rv.BaseRvPresenter;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface NewsFragmentPresenter extends BaseRvPresenter<NewsFragmentView> {

    void loadData(final boolean pullToRefresh, String category, Context context);

    void startAutoPlay();

    void stopAutoPlay();
}
