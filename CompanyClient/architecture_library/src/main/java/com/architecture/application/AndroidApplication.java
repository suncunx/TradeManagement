package com.architecture.application;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.di.components.DaggerApplicationComponent;
import com.architecture.di.modules.ApplicationModule;
import com.architecture.util.BasePreferUtil;
import com.blankj.utilcode.util.Utils;
import com.mob.MobApplication;

/**
 * 程序启动的入口,可以通过这个类管理所有的Activity
 */
public class AndroidApplication extends MobApplication {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        BasePreferUtil.init(this);
        Utils.init(this);
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
