package com.trade.user.register.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.trade.user.register.mvp.RegisterPresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {RegisterPresenterModule.class})
public interface RegisterPresenterComponent {
    void inject(RegisterPresenterImpl presenter);
}
