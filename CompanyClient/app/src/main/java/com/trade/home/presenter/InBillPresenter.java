package com.trade.home.presenter;

import com.architecture.mvp.BasePresenter;
import com.trade.home.view.InBillView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface InBillPresenter extends BasePresenter<InBillView> {
    InBillPresenterImpl.InBillClickListener getClickListener();
}
