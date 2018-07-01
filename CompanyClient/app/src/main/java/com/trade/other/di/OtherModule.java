package com.trade.other.di;

import com.architecture.di.PerFragment;
import com.trade.other.presenter.CustomerPresenter;
import com.trade.other.presenter.CustomerPresenterImpl;
import com.trade.other.presenter.DeliverPresenter;
import com.trade.other.presenter.DeliverPresenterImpl;
import com.trade.other.presenter.FinancePresenter;
import com.trade.other.presenter.FinancePresenterImpl;
import com.trade.other.presenter.OtherPresenter;
import com.trade.other.presenter.OtherPresenterImpl;
import com.trade.other.presenter.SupplierPresenter;
import com.trade.other.presenter.SupplierPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

@PerFragment
@Module
public class OtherModule {

    @PerFragment
    @Provides
    OtherPresenter provideOtherPresenter() {
        return new OtherPresenterImpl();
    }

    @PerFragment
    @Provides
    CustomerPresenter provideCustomerActivityPresenter() {
        return new CustomerPresenterImpl();
    }

    @PerFragment
    @Provides
    SupplierPresenter provideSupplierActivityPresenter() {
        return new SupplierPresenterImpl();
    }

    @PerFragment
    @Provides
    DeliverPresenter provideDeliverActivityPresenter() {
        return new DeliverPresenterImpl();
    }

    @PerFragment
    @Provides
    FinancePresenter provideFinanceActivityPresenter() {
        return new FinancePresenterImpl();
    }
}
