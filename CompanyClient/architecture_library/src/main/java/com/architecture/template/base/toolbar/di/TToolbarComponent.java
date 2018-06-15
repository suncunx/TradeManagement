package com.architecture.template.base.toolbar.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.di.modules.ActivityModule;
import com.architecture.template.base.activity.TActivity;
import com.architecture.template.base.activity.di.*;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, com.architecture.template.base.activity.di.TActivityModule.class})
public interface TToolbarComponent {
    void inject(TActivity activity);
}
