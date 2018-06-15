package com.trade.goods.detail.di;

import com.architecture.di.PerActivity;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.di.modules.ActivityModule;
import com.trade.goods.detail.GoodsDetailActivity;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, GoodsDetailModule.class})
public interface GoodsDetailComponent {
    void inject(GoodsDetailActivity activity);
}
