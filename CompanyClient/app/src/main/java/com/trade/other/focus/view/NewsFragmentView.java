package com.trade.other.focus.view;

import android.support.v4.widget.SwipeRefreshLayout;

import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseRvView;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface NewsFragmentView extends BaseRvView<AdapterBean> {
    SwipeRefreshLayout getContentView();

}
