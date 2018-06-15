package com.trade.other.customer.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.trade.other.customer.mvp.CustomerPresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {CustomerPresenterModule.class})
public interface CustomerPresenterComponent {
    void inject(CustomerPresenterImpl presenter);
}
