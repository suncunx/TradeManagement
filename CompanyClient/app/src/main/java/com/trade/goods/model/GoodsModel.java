package com.trade.goods.model;

import com.trade.listener.HttpCallback;
import com.trade.model.SimpleResultBean;
import com.trade.other.model.SupplierResultBean;

/**
 * Created by Stephen Sun on 2018/7/1 0001.
 * Email:suncunx@qq.com
 */

public interface GoodsModel {
    void listGoods(HttpCallback<GoodsResultBean> callback);

    void listSupplier(HttpCallback<SupplierResultBean> callback);

//    void saveGoods(HttpCallback callback);

    void removeGoods(String goodsId, String supplierId, HttpCallback<SimpleResultBean> callback);

//    void updateGoods(HttpCallback callback);
}
