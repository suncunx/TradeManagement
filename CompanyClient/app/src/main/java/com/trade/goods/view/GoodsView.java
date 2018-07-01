package com.trade.goods.view;

import android.app.Activity;

import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseRvView;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface GoodsView extends BaseRvView<AdapterBean> {
    void refreshGoods();

    Activity getGoodsActivity();
}
