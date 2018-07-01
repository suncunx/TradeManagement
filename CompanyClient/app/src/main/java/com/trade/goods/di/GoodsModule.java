package com.trade.goods.di;

import com.architecture.di.PerFragment;
import com.trade.goods.presenter.GoodsDetailPresenter;
import com.trade.goods.presenter.GoodsDetailPresenterImpl;
import com.trade.goods.presenter.GoodsPresenter;
import com.trade.goods.presenter.GoodsPresenterImpl;
import com.trade.goods.presenter.GoodsSavePresenter;
import com.trade.goods.presenter.GoodsSavePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

@PerFragment
@Module
public class GoodsModule {

    @PerFragment
    @Provides
    GoodsPresenter provideGoodsPresenter() {
        return new GoodsPresenterImpl();
    }

    @PerFragment
    @Provides
    GoodsDetailPresenter provideGoodsDetailPresenter() {
        return new GoodsDetailPresenterImpl();
    }

    @PerFragment
    @Provides
    GoodsSavePresenter provideGoodsSavePresenter() {
        return new GoodsSavePresenterImpl();
    }
//    @PerFragment
//    @Provides
//    GoodsAdapter provideMessageAdapter() {
//        return new GoodsAdapter(R.layout.item_list_message);
//    }
}
