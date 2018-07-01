package com.trade.home.presenter;

import com.architecture.di.components.ApplicationComponent;
import com.architecture.mvp.BasePresenterImpl;
import com.trade.home.view.InBillView;
import com.trade.home.model.InBillResultBean;
import com.trade.util.PhoneUtil;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class InBillPresenterImpl extends BasePresenterImpl<InBillView> implements InBillPresenter {

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        // 可有可无，需要网络操作的时候一般需要
    }

    @Override
    public InBillClickListener getClickListener() {
        return new InBillClickListener();
    }

    public class InBillClickListener {
        public void onClickLeft(InBillResultBean.ResultBean.InBillBean inBillBean) {
            PhoneUtil.callPhone(getView().getApplicationComponent().context(), inBillBean.getSupplierPhone());
        }

        public void onClickRight(InBillResultBean.ResultBean.InBillBean inBillBean) {
        }
    }

}
