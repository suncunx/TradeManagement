package com.trade.other.presenter;

import android.view.View;

import com.architecture.mvp.rv.BaseRvPresenter;
import com.trade.other.view.CustomerView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface CustomerPresenter extends BaseRvPresenter<CustomerView> {
    void loadData(final boolean pullToRefresh);

    View.OnClickListener getClickListener();
}
