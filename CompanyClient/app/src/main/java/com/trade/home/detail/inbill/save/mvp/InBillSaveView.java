package com.trade.home.detail.inbill.save.mvp;


import android.app.Activity;

import com.architecture.model.DropMenuBean;
import com.architecture.mvp.lce.BaseLceView;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface InBillSaveView extends BaseLceView<DropMenuBean> {
    Activity getActivity();
}
