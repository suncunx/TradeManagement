package com.trade.other.finance.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.di.modules.ActivityModule;
import com.trade.other.finance.FinanceActivity;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, FinanceModule.class})
public interface FinanceComponent {
    void inject(FinanceActivity activity);
}