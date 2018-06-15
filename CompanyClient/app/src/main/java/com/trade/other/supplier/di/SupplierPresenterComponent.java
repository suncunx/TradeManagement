package com.trade.other.supplier.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.trade.other.supplier.mvp.SupplierPresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {SupplierPresenterModule.class})
public interface SupplierPresenterComponent {
    void inject(SupplierPresenterImpl presenter);
}
