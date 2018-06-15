package com.architecture.template.rv.activity.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.di.modules.ActivityModule;
import com.architecture.template.rv.activity.TRvActivity;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, TRvActivityModule.class})
public interface TRvActivityComponent {
    void inject(TRvActivity rvActivity);
}
