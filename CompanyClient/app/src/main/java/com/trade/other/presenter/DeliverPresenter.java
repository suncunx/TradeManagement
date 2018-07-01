package com.trade.other.presenter;

import android.view.View;

import com.architecture.mvp.rv.BaseRvPresenter;
import com.trade.other.view.DeliverView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface DeliverPresenter extends BaseRvPresenter<DeliverView> {
    void loadData(final boolean pullToRefresh);

    View.OnClickListener getClickListener();
}
