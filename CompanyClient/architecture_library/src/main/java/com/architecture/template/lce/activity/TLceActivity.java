package com.architecture.template.lce.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.architecture.R;
import com.architecture.mvp.lce.BaseLceActivity;
import com.architecture.template.base.toolbar.TToolbarActivity;
import com.architecture.template.lce.activity.model.TLceBean;
import com.architecture.template.lce.activity.mvp.TLceActivityPresenter;
import com.architecture.template.lce.activity.mvp.TLceActivityView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TLceActivity extends BaseLceActivity<View, TLceBean, TLceActivityView, TLceActivityPresenter> implements TLceActivityView {

    //TODO 改变Activity,设置参数
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TLceActivity.class);
    }
    @Override
    public void initializeInjector() {
        //TODO 初始化Component
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        //TODO 修改布局文件
        return DataBindingUtil.setContentView(this, R.layout.template);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void setData(TLceBean data) {
        // TODO 将数据绑定到View中
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }
}
