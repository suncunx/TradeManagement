package com.trade.home.presenter;

import com.architecture.mvp.BasePresenter;
import com.trade.home.view.OutBillView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface OutBillPresenter extends BasePresenter<OutBillView> {
    OutBillPresenterImpl.OutBillClickListener getClickListener();
}
