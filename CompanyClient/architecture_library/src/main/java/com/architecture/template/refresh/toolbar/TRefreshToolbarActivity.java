package com.architecture.template.refresh.toolbar;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;

import com.architecture.R;
import com.architecture.mvp.refresh.BaseRefreshToolbarActivity;
import com.architecture.template.refresh.activity.TRefreshActivity;
import com.architecture.template.refresh.toolbar.model.TRefreshBean;
import com.architecture.template.refresh.toolbar.mvp.TRefreshToolbarActivityPresenter;
import com.architecture.template.refresh.toolbar.mvp.TRefreshToolbarActivityView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TRefreshToolbarActivity extends BaseRefreshToolbarActivity<SwipeRefreshLayout, TRefreshBean, TRefreshToolbarActivityView, TRefreshToolbarActivityPresenter> implements TRefreshToolbarActivityView {


    //TODO 改变Activity,设置参数
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TRefreshToolbarActivity.class);
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
    public void setData(TRefreshBean data) {
        // TODO 将数据绑定到View中
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

    @Override
    protected String createTitle() {
        return null;
    }

    @Override
    public void onRefresh() {

    }
}
