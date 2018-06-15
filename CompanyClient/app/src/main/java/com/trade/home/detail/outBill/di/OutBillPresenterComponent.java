package com.trade.home.detail.outBill.di;

import com.architecture.di.PerFragment;
import com.architecture.di.components.ApplicationComponent;
import com.trade.home.detail.outBill.mvp.OutBillPresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */
@PerFragment
@Component(dependencies = {ApplicationComponent.class}, modules = {OutBillPresenterModule.class})
public interface OutBillPresenterComponent {
    void inject(OutBillPresenterImpl presenter);
}
