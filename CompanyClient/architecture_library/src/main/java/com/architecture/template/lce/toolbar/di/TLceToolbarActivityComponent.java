package com.architecture.template.lce.toolbar.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.di.modules.ActivityModule;
import com.architecture.template.lce.toolbar.TLceToolbarActivity;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, TLceToolbarActivityModule.class})
public interface TLceToolbarActivityComponent {
    void inject(TLceToolbarActivity activity);
}
