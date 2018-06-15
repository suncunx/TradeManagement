package com.trade.other.supplier.mvp;

import android.view.View;

import com.architecture.mvp.rv.BaseRvPresenter;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface SupplierPresenter extends BaseRvPresenter<SupplierView> {
    void loadData(final boolean pullToRefresh);

    View.OnClickListener getClickListener();
}
