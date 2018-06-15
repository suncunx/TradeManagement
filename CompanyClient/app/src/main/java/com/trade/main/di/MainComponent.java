package com.trade.main.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ActivityComponent;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.di.modules.ActivityModule;
import com.trade.main.MainActivity;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/4 0004.
 * Email:suncunx@qq.com
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MainModule.class})
public interface MainComponent extends ActivityComponent {
    void inject(MainActivity mainActivity);
}
