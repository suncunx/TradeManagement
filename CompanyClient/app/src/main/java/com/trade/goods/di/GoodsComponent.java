package com.trade.goods.di;

import com.architecture.di.PerFragment;
import com.trade.goods.GoodsFragment;

import dagger.Component;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
@PerFragment
@Component(modules = GoodsModule.class)
public interface GoodsComponent {

    void inject(GoodsFragment goodsFragment);
}
