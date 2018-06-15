package com.trade.other.supplier.di;

import com.architecture.di.PerActivity;
import com.trade.other.supplier.mvp.SupplierPresenter;
import com.trade.other.supplier.mvp.SupplierPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class SupplierModule {
    @PerActivity
    @Provides
    SupplierPresenter provideSupplierActivityPresenter() {
        return new SupplierPresenterImpl();
    }
}
