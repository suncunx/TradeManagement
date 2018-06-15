package com.trade.home.detail.outBill.save.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.trade.home.detail.outBill.save.mvp.OutBillSavePresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {OutBillSavePresenterModule.class})
public interface OutBillSavePresenterComponent {
    void inject(OutBillSavePresenterImpl presenter);
}
