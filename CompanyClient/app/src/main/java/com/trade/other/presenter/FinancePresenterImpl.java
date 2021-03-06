package com.trade.other.presenter;

import android.util.Log;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.lce.BaseNovateLcePresenterImpl;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.trade.model.FinanceResultBean;
import com.trade.other.di.DaggerOtherPresenterComponent;
import com.trade.other.di.OtherPresenterModule;
import com.trade.other.model.FinanceService;
import com.trade.other.view.FinanceView;

import static com.tamic.novate.Novate.TAG;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class FinancePresenterImpl extends BaseNovateLcePresenterImpl<FinanceView, FinanceService> implements FinancePresenter {

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        DaggerOtherPresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .otherPresenterModule(new OtherPresenterModule())
                .build()
                .inject(this);
    }

    @Override
    public void loadData(final boolean pullToRefresh, final String year) {
        getView().showLoading(pullToRefresh);

        novate.call(service.getFinances(year), new BaseSubscriber<FinanceResultBean>() {
            @Override
            public void onError(Throwable e) {
                if (getView() != null)
                    getView().showError(e, pullToRefresh);
            }

            @Override
            public void onNext(FinanceResultBean financeResultBean) {
                int count = 0;
                for (FinanceResultBean.ResultBean.FinanceMonthBean bean : financeResultBean.getResult().getFinances()) {
                    if (bean.getProfit() == 0) {
                        count++;
                    } else {
                        break;
                    }
                }
                Log.d(TAG, "setData: year = " + year);
                Log.d(TAG, "setData: count = " + count);
                if (count == 12) {
                    getView().showError(new Exception(), false);
                    return;
                }

                getView().setData(financeResultBean);
                getView().showContent();
            }
        });
    }
}
