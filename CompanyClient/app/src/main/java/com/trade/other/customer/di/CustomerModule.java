package com.trade.other.customer.di;

import com.architecture.di.PerActivity;
import com.trade.other.customer.mvp.CustomerPresenter;
import com.trade.other.customer.mvp.CustomerPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class CustomerModule {
    @PerActivity
    @Provides
    CustomerPresenter provideCustomerActivityPresenter() {
        return new CustomerPresenterImpl();
    }
}
