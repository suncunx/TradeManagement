package com.trade.home.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.architecture.mvp.BaseToolbarActivity;
import com.trade.BR;
import com.trade.R;
import com.trade.home.presenter.InBillPresenter;
import com.trade.home.view.InBillView;
import com.trade.home.di.DaggerHomeComponent;
import com.trade.home.di.HomeModule;
import com.trade.home.model.InBillResultBean;

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
        DaggerHomeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .homeModule(new HomeModule())
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
