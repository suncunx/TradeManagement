package com.trade.other.finance.di;

import com.architecture.di.PerActivity;
import com.trade.other.finance.mvp.FinancePresenter;
import com.trade.other.finance.mvp.FinancePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class FinanceModule {
    @PerActivity
    @Provides
    FinancePresenter provideFinanceActivityPresenter() {
        return new FinancePresenterImpl();
    }
}
