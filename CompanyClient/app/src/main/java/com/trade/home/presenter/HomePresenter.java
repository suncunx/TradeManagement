package com.trade.home.presenter;

import android.support.v4.app.FragmentManager;

import com.architecture.mvp.rv.BaseRvPresenter;
import com.trade.home.view.HomeView;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface HomePresenter extends BaseRvPresenter<HomeView> {

    void loadTitles(final boolean pullToRefresh, FragmentManager fragmentManager);

    void onTitleChange(boolean outBill);
}
