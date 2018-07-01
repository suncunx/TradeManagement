package com.trade.goods.presenter;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.BasePresenterImpl;
import com.trade.goods.view.GoodsDetailView;
import com.trade.goods.model.GoodsResultBean;
import com.trade.util.PhoneUtil;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class GoodsDetailPresenterImpl extends BasePresenterImpl<GoodsDetailView> implements GoodsDetailPresenter {

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        // 可有可无，需要网络操作的时候一般需要
    }

    @Override
    public GoodsClickListener getClickListener() {
        return new GoodsClickListener();
    }

    public class GoodsClickListener {
        public void onClickLeft(GoodsResultBean.ResultBean.GoodsBean goodsBean) {
            PhoneUtil.callPhone(getView().getApplicationComponent().context(), goodsBean.getSupplierPhone());
        }
    }

}
