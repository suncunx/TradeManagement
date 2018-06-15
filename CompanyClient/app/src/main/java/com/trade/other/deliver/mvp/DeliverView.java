package com.trade.other.deliver.mvp;

import android.app.Activity;

import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseRvView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface DeliverView extends BaseRvView<AdapterBean> {
    Activity getActivity();
}
