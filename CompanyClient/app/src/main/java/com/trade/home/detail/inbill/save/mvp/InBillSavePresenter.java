package com.trade.home.detail.inbill.save.mvp;

import com.architecture.mvp.lce.BaseLcePresenter;
import com.trade.model.InBillResultBean;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface InBillSavePresenter extends BaseLcePresenter<InBillSaveView> {

    void loadGoods(boolean pullToRefresh, InBillResultBean.ResultBean.InBillBean inBillBean);
}
