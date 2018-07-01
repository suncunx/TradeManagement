package com.trade.goods.presenter;

import com.architecture.mvp.BasePresenter;
import com.trade.goods.view.GoodsDetailView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public interface GoodsDetailPresenter extends BasePresenter<GoodsDetailView> {
    GoodsDetailPresenterImpl.GoodsClickListener getClickListener();
}
