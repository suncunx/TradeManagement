package com.trade.goods.model;

import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.trade.listener.HttpCallback;
import com.trade.model.SimpleResultBean;
import com.trade.other.model.SupplierResultBean;

/**
 * Created by Stephen Sun on 2018/7/1 0001.
 * Email:suncunx@qq.com
 */

public class GoodsModelImpl implements GoodsModel {

    Novate novate;
    GoodsService service;

    public GoodsModelImpl(Novate novate) {
        this.novate = novate;
        service = novate.create(GoodsService.class);
    }

    @Override
    public void listGoods(final HttpCallback<GoodsResultBean> callback) {
        novate.call(service.getGoodss(), new BaseSubscriber<GoodsResultBean>() {

            @Override
            public void onError(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onNext(GoodsResultBean resultBean) {
                callback.onSuccess(resultBean);
            }
        });
    }

    @Override
    public void listSupplier(final HttpCallback<SupplierResultBean> callback) {
        novate.call(service.getSuppliers(), new BaseSubscriber<SupplierResultBean>() {
            @Override
            public void onError(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onNext(SupplierResultBean resultBean) {
                callback.onSuccess(resultBean);
            }
        });
    }

    @Override
    public void removeGoods(String goodsId, String supplierId, final HttpCallback<SimpleResultBean> callback) {
        novate.call(service.removeGoods(goodsId, supplierId), new BaseSubscriber<SimpleResultBean>() {
            @Override
            public void onError(Throwable e) {
                callback.onFailure(e);
            }

            @Override
            public void onNext(SimpleResultBean simpleResultBean) {
                callback.onSuccess(simpleResultBean);
            }
        });
    }
}
