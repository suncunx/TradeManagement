package com.trade.other.finance.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.trade.other.finance.mvp.FinancePresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {FinancePresenterModule.class})
public interface FinancePresenterComponent {
    void inject(FinancePresenterImpl presenter);
}
