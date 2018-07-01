package com.trade.home.presenter;

import com.architecture.mvp.lce.BaseLcePresenter;
import com.trade.home.view.OutBillSaveView;
import com.trade.home.model.OutBillResultBean;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface OutBillSavePresenter extends BaseLcePresenter<OutBillSaveView> {

    void loadGoods(boolean pullToRefresh, OutBillResultBean.ResultBean.OutBillBean outBillBean);
}
