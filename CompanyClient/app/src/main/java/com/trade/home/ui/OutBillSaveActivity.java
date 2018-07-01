package com.trade.home.ui;

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
import com.trade.home.presenter.OutBillSavePresenter;
import com.trade.home.view.OutBillSaveView;
import com.trade.home.di.DaggerHomeComponent;
import com.trade.home.di.HomeModule;
import com.trade.home.model.OutBillResultBean;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class OutBillSaveActivity extends BaseLceToolbarActivity<LinearLayout, DropMenuBean, OutBillSaveView, OutBillSavePresenter> implements OutBillSaveView {

    private static final String EXTRA = "inBillBean";
    private static final String TITLE = "新的出货";

    private static final String TITLE_UPDATE = "修改出货";
    private OutBillResultBean.ResultBean.OutBillBean outBillBean;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, OutBillSaveActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public static Intent getCallingIntent(Context context, OutBillResultBean.ResultBean.OutBillBean outBillBean) {
        Intent intent = new Intent(context, OutBillSaveActivity.class);
        intent.putExtra("outBillBean", outBillBean);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        outBillBean = (OutBillResultBean.ResultBean.OutBillBean) getIntent().getSerializableExtra("outBillBean");
        super.onCreate(savedInstanceState);
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
    protected String createTitle() {
        return outBillBean == null ? TITLE : TITLE_UPDATE;
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_out_bill_save);
    }

    @Override
    public void setData(DropMenuBean data) {
        LogUtils.d(data);
        viewDataBinding.setVariable(BR.dropMenuBean, data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadGoods(pullToRefresh, outBillBean);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
