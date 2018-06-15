package com.trade.home.detail.outBill.mvp;

import com.architecture.mvp.BasePresenter;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface OutBillPresenter extends BasePresenter<OutBillView> {
    OutBillPresenterImpl.OutBillClickListener getClickListener();
}
