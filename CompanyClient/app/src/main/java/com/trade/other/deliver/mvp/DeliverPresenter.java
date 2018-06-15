package com.trade.other.deliver.mvp;

import android.view.View;

import com.architecture.mvp.rv.BaseRvPresenter;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface DeliverPresenter extends BaseRvPresenter<DeliverView> {
    void loadData(final boolean pullToRefresh);

    View.OnClickListener getClickListener();
}
