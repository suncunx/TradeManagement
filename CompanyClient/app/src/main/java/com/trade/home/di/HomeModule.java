package com.trade.home.di;

import com.architecture.di.PerFragment;
import com.trade.home.presenter.InBillPresenter;
import com.trade.home.presenter.InBillPresenterImpl;
import com.trade.home.presenter.InBillSavePresenter;
import com.trade.home.presenter.InBillSavePresenterImpl;
import com.trade.home.presenter.OutBillPresenter;
import com.trade.home.presenter.OutBillPresenterImpl;
import com.trade.home.presenter.OutBillSavePresenter;
import com.trade.home.presenter.OutBillSavePresenterImpl;
import com.trade.home.presenter.HomePresenter;
import com.trade.home.presenter.HomePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

@PerFragment
@Module
public class HomeModule {

    @PerFragment
    @Provides
    HomePresenter provideHomePresenter() {
        return new HomePresenterImpl();
    }

    @PerFragment
    @Provides
    InBillPresenter provideInBillPresenter() {
        return new InBillPresenterImpl();
    }

    @PerFragment
    @Provides
    InBillSavePresenter provideInBillSavePresenter() {
        return new InBillSavePresenterImpl();
    }

    @PerFragment
    @Provides
    OutBillSavePresenter provideOutBillSavePresenter() {
        return new OutBillSavePresenterImpl();
    }

    @PerFragment
    @Provides
    OutBillPresenter provideOutBillPresenter() {
        return new OutBillPresenterImpl();
    }
}
