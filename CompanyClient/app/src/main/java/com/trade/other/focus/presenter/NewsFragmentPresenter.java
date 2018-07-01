package com.trade.other.focus.presenter;

import android.content.Context;

import com.architecture.mvp.rv.BaseRvPresenter;
import com.trade.other.focus.view.NewsFragmentView;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface NewsFragmentPresenter extends BaseRvPresenter<NewsFragmentView> {

    void loadData(final boolean pullToRefresh, String category, Context context);

    void startAutoPlay();

    void stopAutoPlay();
}
