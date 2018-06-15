package com.trade.goods.save.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.trade.goods.save.mvp.GoodsSavePresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {GoodsSavePresenterModule.class})
public interface GoodsSavePresenterComponent {
    void inject(GoodsSavePresenterImpl presenter);
}
