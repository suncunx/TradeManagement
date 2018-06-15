package com.trade.home.detail.outBill.di;

import com.architecture.di.PerActivity;
import com.trade.home.detail.outBill.mvp.OutBillPresenter;
import com.trade.home.detail.outBill.mvp.OutBillPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class OutBillModule {
    @PerActivity
    @Provides
    OutBillPresenter provideOutBillPresenter() {
        return new OutBillPresenterImpl();
    }
}
