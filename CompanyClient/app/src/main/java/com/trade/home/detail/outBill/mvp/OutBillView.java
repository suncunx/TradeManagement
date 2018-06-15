package com.trade.home.detail.outBill.mvp;

import android.app.Activity;

import com.architecture.mvp.BaseView;
import com.trade.model.OutBillResultBean;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface OutBillView extends BaseView {
    Activity getActivity();

    OutBillResultBean.ResultBean.OutBillBean getOutBillBean();
}
