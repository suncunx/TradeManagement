package com.trade.goods.di;

import com.architecture.di.PerFragment;
import com.architecture.di.components.ApplicationComponent;
import com.trade.goods.ui.GoodsFragment;
import com.trade.goods.ui.GoodsDetailActivity;
import com.trade.goods.ui.GoodsSaveActivity;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {GoodsModule.class})
public interface GoodsComponent {

    void inject(GoodsFragment goodsFragment);

    void inject(GoodsDetailActivity activity);

    void inject(GoodsSaveActivity activity);
}
