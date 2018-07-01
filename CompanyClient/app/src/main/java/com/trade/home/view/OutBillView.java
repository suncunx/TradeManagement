package com.trade.home.view;

import android.app.Activity;

import com.architecture.mvp.BaseView;
import com.trade.home.model.OutBillResultBean;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface OutBillView extends BaseView {
    Activity getActivity();

    OutBillResultBean.ResultBean.OutBillBean getOutBillBean();
}
