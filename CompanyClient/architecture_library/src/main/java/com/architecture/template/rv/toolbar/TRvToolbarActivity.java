package com.architecture.template.rv.toolbar;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;

import com.architecture.R;
import com.architecture.mvp.rv.BaseRvToolbarActivity;
import com.architecture.template.refresh.toolbar.TRefreshToolbarActivity;
import com.architecture.template.rv.toolbar.model.TRvToolbarBean;
import com.architecture.template.rv.toolbar.mvp.TRvToolbarActivityPresenter;
import com.architecture.template.rv.toolbar.mvp.TRvToolbarActivityView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TRvToolbarActivity extends BaseRvToolbarActivity<SwipeRefreshLayout, TRvToolbarBean, TRvToolbarActivityView, TRvToolbarActivityPresenter> implements TRvToolbarActivityView {

    //TODO 改变Activity,设置参数
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TRvToolbarActivity.class);
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
    public void setData(TRvToolbarBean data) {
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

}
