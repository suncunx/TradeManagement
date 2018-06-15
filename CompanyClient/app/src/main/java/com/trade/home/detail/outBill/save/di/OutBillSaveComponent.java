package com.trade.home.detail.outBill.save.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.di.modules.ActivityModule;
import com.trade.home.detail.outBill.save.OutBillSaveActivity;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, OutBillSaveModule.class})
public interface OutBillSaveComponent {

    void inject(OutBillSaveActivity activity);
}
