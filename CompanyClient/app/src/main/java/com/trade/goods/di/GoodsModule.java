package com.trade.goods.di;

import com.architecture.di.PerFragment;
import com.trade.goods.mvp.GoodsPresenter;
import com.trade.goods.mvp.GoodsPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

@PerFragment
@Module
public class GoodsModule {

    @PerFragment
    @Provides
    GoodsPresenter provideGoodsPresenter() {
        return new GoodsPresenterImpl();
    }

//    @PerFragment
//    @Provides
//    GoodsAdapter provideMessageAdapter() {
//        return new GoodsAdapter(R.layout.item_list_message);
//    }
}
