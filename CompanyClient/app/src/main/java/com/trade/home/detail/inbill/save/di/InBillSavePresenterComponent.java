package com.trade.home.detail.inbill.save.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.trade.home.detail.inbill.save.mvp.InBillSavePresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {InBillSavePresenterModule.class})
public interface InBillSavePresenterComponent {
    void inject(InBillSavePresenterImpl presenter);
}
