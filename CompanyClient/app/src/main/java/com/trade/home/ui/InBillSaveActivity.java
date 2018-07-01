package com.trade.home.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.apkfuns.logutils.LogUtils;
import com.architecture.model.DropMenuBean;
import com.architecture.mvp.lce.BaseLceToolbarActivity;
import com.trade.BR;
import com.trade.R;
import com.trade.home.presenter.InBillSavePresenter;
import com.trade.home.view.InBillSaveView;
import com.trade.home.di.DaggerHomeComponent;
import com.trade.home.di.HomeModule;
import com.trade.home.model.InBillResultBean;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class InBillSaveActivity extends BaseLceToolbarActivity<LinearLayout, DropMenuBean, InBillSaveView, InBillSavePresenter> implements InBillSaveView {

    private static final String EXTRA = "inBillBean";
    private static final String TITLE = "新的进货";
    private static final String TITLE_UPDATE = "修改进货";
    private InBillResultBean.ResultBean.InBillBean inBillBean;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, InBillSaveActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
    public static Intent getCallingIntent(Context context, InBillResultBean.ResultBean.InBillBean inBillBean) {
        Intent intent = new Intent(context, InBillSaveActivity.class);
        intent.putExtra("inBillBean", inBillBean);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
    @Override
    public void init() {
        loadData(false);
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
    protected void onCreate(Bundle savedInstanceState) {
        inBillBean = (InBillResultBean.ResultBean.InBillBean) getIntent().getSerializableExtra("inBillBean");
        super.onCreate(savedInstanceState);
    }
    @Override
    protected String createTitle() {
        return inBillBean == null ? TITLE : TITLE_UPDATE;
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_in_bill_save);
    }

    @Override
    public void setData(DropMenuBean data) {
        LogUtils.d(data);
        viewDataBinding.setVariable(BR.dropMenuBean, data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadGoods(pullToRefresh, inBillBean);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
