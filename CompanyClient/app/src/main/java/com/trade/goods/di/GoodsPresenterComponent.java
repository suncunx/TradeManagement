package com.trade.goods.di;

import com.architecture.di.PerFragment;
import com.architecture.di.components.ApplicationComponent;
import com.trade.goods.mvp.GoodsPresenterImpl;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */
@PerFragment
@Component(dependencies = {ApplicationComponent.class}, modules = {GoodsPresenterModule.class})
public interface GoodsPresenterComponent {
    void inject(GoodsPresenterImpl presenter);
}
