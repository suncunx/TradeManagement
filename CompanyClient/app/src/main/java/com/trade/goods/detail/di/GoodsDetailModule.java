package com.trade.goods.detail.di;

import com.architecture.di.PerActivity;
import com.trade.goods.detail.mvp.GoodsDetailPresenter;
import com.trade.goods.detail.mvp.GoodsDetailPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class GoodsDetailModule {
    @PerActivity
    @Provides
    GoodsDetailPresenter provideGoodsDetailPresenter() {
        return new GoodsDetailPresenterImpl();
    }
}
