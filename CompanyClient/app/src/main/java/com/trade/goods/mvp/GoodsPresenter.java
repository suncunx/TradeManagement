package com.trade.goods.mvp;

import com.architecture.mvp.rv.BaseRvPresenter;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface GoodsPresenter extends BaseRvPresenter<GoodsView> {

    void loadGoods();

    void refreshGoods();
}
