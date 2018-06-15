package com.trade.other.deliver.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.trade.other.deliver.mvp.DeliverPresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {DeliverPresenterModule.class})
public interface DeliverPresenterComponent {
    void inject(DeliverPresenterImpl presenter);
}
