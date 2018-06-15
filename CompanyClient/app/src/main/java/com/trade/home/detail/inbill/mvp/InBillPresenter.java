package com.trade.home.detail.inbill.mvp;

import com.architecture.mvp.BasePresenter;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface InBillPresenter extends BasePresenter<InBillView> {
    InBillPresenterImpl.InBillClickListener getClickListener();
}
