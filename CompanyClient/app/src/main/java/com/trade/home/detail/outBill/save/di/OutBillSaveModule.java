package com.trade.home.detail.outBill.save.di;

import com.architecture.di.PerActivity;
import com.trade.home.detail.outBill.save.mvp.OutBillSavePresenter;
import com.trade.home.detail.outBill.save.mvp.OutBillSavePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

@PerActivity
@Module
public class OutBillSaveModule {

    @PerActivity
    @Provides
    OutBillSavePresenter provideOutBillSavePresenter() {
        return new OutBillSavePresenterImpl();
    }
}
