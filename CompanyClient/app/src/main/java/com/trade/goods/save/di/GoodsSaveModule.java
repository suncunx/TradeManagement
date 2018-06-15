package com.trade.goods.save.di;

import com.architecture.di.PerActivity;
import com.trade.goods.save.mvp.GoodsSavePresenter;
import com.trade.goods.save.mvp.GoodsSavePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

@PerActivity
@Module
public class GoodsSaveModule {

    @PerActivity
    @Provides
    GoodsSavePresenter provideGoodsSavePresenter() {
        return new GoodsSavePresenterImpl();
    }
}
