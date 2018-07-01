package com.trade.other.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;

import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseRvToolbarActivity;
import com.trade.BR;
import com.trade.R;
import com.trade.other.di.DaggerOtherComponent;
import com.trade.other.di.OtherModule;
import com.trade.other.presenter.CustomerPresenter;
import com.trade.other.view.CustomerView;
import com.trade.util.ApiManager;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 * 适应下拉刷新，上拉加载的纯列表Activity
 */

public class CustomerActivity extends BaseRvToolbarActivity<SwipeRefreshLayout, AdapterBean, CustomerView, CustomerPresenter> implements CustomerView {

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, CustomerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void init() {
        super.init();
        viewDataBinding.setVariable(BR.listener, presenter.getClickListener());
    }

    @Override
    public void initializeInjector() {
        DaggerOtherComponent.builder()
                .applicationComponent(getApplicationComponent())
                .otherModule(new OtherModule())
                .build()
                .inject(this);
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_customer);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        if (e instanceof IndexOutOfBoundsException) {
            return "您还没有客户哦~点击刷新";
        }
        return ApiManager.CONNECT_TIME_OUT;
    }


    @Override
    public void setData(AdapterBean data) {
        viewDataBinding.setVariable(BR.adapterBean, data);
        viewDataBinding.setVariable(BR.listener, presenter.getClickListener());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

    @Override
    protected String createTitle() {
        return "客户";
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
