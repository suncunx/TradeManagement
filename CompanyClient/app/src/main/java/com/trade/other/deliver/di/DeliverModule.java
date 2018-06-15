package com.trade.other.deliver.di;

import com.architecture.di.PerActivity;
import com.trade.other.deliver.mvp.DeliverPresenter;
import com.trade.other.deliver.mvp.DeliverPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class DeliverModule {
    @PerActivity
    @Provides
    DeliverPresenter provideDeliverActivityPresenter() {
        return new DeliverPresenterImpl();
    }
}
