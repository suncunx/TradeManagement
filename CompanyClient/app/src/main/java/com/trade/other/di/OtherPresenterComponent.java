package com.trade.other.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.trade.other.presenter.CustomerPresenterImpl;
import com.trade.other.presenter.DeliverPresenterImpl;
import com.trade.other.presenter.FinancePresenterImpl;
import com.trade.other.presenter.SupplierPresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {OtherPresenterModule.class})
public interface OtherPresenterComponent {
    void inject(CustomerPresenterImpl presenter);

    void inject(SupplierPresenterImpl presenter);

    void inject(DeliverPresenterImpl presenter);

    void inject(FinancePresenterImpl presenter);

//    void inject(CustomerPresenterImpl presenter);
}
