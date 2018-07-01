package com.trade.other.view;

import android.app.Activity;

import com.architecture.model.AdapterBean;
import com.architecture.mvp.BaseView;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface OtherView extends BaseView {

    void setData(AdapterBean data);

    Activity getUserActivity();
}
