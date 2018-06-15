package com.trade.home.detail.inbill.save.di;

import com.architecture.di.PerActivity;
import com.trade.home.detail.inbill.save.mvp.InBillSavePresenter;
import com.trade.home.detail.inbill.save.mvp.InBillSavePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

@PerActivity
@Module
public class InBillSaveModule {

    @PerActivity
    @Provides
    InBillSavePresenter provideInBillSavePresenter() {
        return new InBillSavePresenterImpl();
    }
}
