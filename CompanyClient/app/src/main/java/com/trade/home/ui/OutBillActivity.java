package com.trade.home.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.architecture.mvp.BaseToolbarActivity;
import com.trade.BR;
import com.trade.R;
import com.trade.home.presenter.OutBillPresenter;
import com.trade.home.view.OutBillView;
import com.trade.home.di.DaggerHomeComponent;
import com.trade.home.di.HomeModule;
import com.trade.main.ui.MainActivity;
import com.trade.home.model.OutBillResultBean;
import com.trade.util.Constants;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class OutBillActivity extends BaseToolbarActivity<OutBillView, OutBillPresenter> implements OutBillView, View.OnClickListener {

    private static final String EXTRA = "outBillBean";
    private static final String TITLE = "出货详情";
    private OutBillResultBean.ResultBean.OutBillBean outBillBean;

    public static Intent getCallingIntent(Context context, OutBillResultBean.ResultBean.OutBillBean outBillBean) {
        Intent intent = new Intent(context, OutBillActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(EXTRA, outBillBean);
        return intent;
    }

    @Override
    public void init() {
        outBillBean = (OutBillResultBean.ResultBean.OutBillBean) getIntent().getSerializableExtra(EXTRA);
        viewDataBinding.setVariable(BR.outBillBean, outBillBean);
        viewDataBinding.setVariable(BR.listener, presenter.getClickListener());
        viewDataBinding.setVariable(BR.isButtonVisible, outBillBean.getDeliverStatus().equals(Constants.DELIVER_NOT));
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
        return DataBindingUtil.setContentView(this, R.layout.activity_out_bill);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public OutBillResultBean.ResultBean.OutBillBean getOutBillBean() {
        return outBillBean;
    }

    @Override
    protected View.OnClickListener createBackClickListener() {
        return this;
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
