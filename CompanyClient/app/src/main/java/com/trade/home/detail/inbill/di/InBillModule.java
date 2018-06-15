package com.trade.home.detail.inbill.di;

import com.architecture.di.PerActivity;
import com.trade.home.detail.inbill.mvp.InBillPresenter;
import com.trade.home.detail.inbill.mvp.InBillPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class InBillModule {
    @PerActivity
    @Provides
    InBillPresenter provideInBillPresenter() {
        return new InBillPresenterImpl();
    }
}
