package com.trade.home.view;


import android.app.Activity;

import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseRvView;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface HomeView extends BaseRvView<AdapterBean> {
    Activity getHomeActivity();
}
