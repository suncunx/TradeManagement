package com.trade.goods.save.mvp;

import com.architecture.mvp.lce.BaseLcePresenter;
import com.jph.takephoto.model.TImage;
import com.trade.model.GoodsResultBean;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public interface GoodsSavePresenter extends BaseLcePresenter<GoodsSaveView> {

    void loadSuppliers(boolean pullToRefresh, GoodsResultBean.ResultBean.GoodsBean goodsBean);

    void showImage(TImage image);

    void dismissProgressDialog();
}
