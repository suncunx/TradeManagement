package com.architecture.template.rv.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;

import com.architecture.R;
import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseRvActivity;
import com.architecture.template.refresh.toolbar.TRefreshToolbarActivity;
import com.architecture.template.rv.activity.mvp.TRvActivityPresenter;
import com.architecture.template.rv.activity.mvp.TRvActivityView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 * 适应下拉刷新，上拉加载的纯列表Activity
 */

public class TRvActivity extends BaseRvActivity<SwipeRefreshLayout, AdapterBean, TRvActivityView, TRvActivityPresenter> implements TRvActivityView {

    //TODO 改变Activity,设置参数
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TRvActivity.class);
    }

    @Override
    public void initializeInjector() {
        //TODO 初始化Component
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        //TODO 修改布局文件
        return DataBindingUtil.setContentView(this, R.layout.common_lce_view);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }


    @Override
    public void setData(AdapterBean data) {
        //        // TODO 将数据绑定到View中

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

}
