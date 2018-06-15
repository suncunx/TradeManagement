package com.trade.home.detail.inbill;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.architecture.mvp.BaseToolbarActivity;
import com.trade.BR;
import com.trade.R;
import com.trade.home.detail.inbill.di.DaggerInBillComponent;
import com.trade.home.detail.inbill.di.InBillModule;
import com.trade.home.detail.inbill.mvp.InBillPresenter;
import com.trade.home.detail.inbill.mvp.InBillView;
import com.trade.model.InBillResultBean;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class InBillActivity extends BaseToolbarActivity<InBillView, InBillPresenter> implements InBillView {

    private static final String EXTRA = "inBillBean";
    private static final String TITLE = "进货详情";

    public static Intent getCallingIntent(Context context, InBillResultBean.ResultBean.InBillBean inBillBean) {
        Intent intent = new Intent(context, InBillActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA, inBillBean);
        return intent;
    }

    @Override
    public void init() {
        InBillResultBean.ResultBean.InBillBean inBillBean = (InBillResultBean.ResultBean.InBillBean) getIntent().getSerializableExtra(EXTRA);
        viewDataBinding.setVariable(BR.inBillBean, inBillBean);
        viewDataBinding.setVariable(BR.listener, presenter.getClickListener());
        viewDataBinding.setVariable(BR.isButtonVisible, false);
    }

    @Override
    public void initializeInjector() {
        DaggerInBillComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .inBillModule(new InBillModule())
                .build()
                .inject(this);
    }

    @Override
    protected String createTitle() {
        return TITLE;
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_in_bill);
    }
}
