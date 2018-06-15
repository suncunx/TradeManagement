package com.architecture.template.refresh.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.architecture.R;
import com.architecture.mvp.lce.BaseLceActivity;
import com.architecture.template.refresh.activity.model.TLceBean;
import com.architecture.template.refresh.activity.mvp.TRefreshActivityPresenter;
import com.architecture.template.refresh.activity.mvp.TRefreshActivityView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 * 刷新的Activity必须要用LceActivity才方便操作，因为这样才能确定contentView是SwipeRefreshLayout，也不需要再
 * 重新继承BaseActivity考虑那么多种情况
 */

public class TRefreshActivity extends BaseLceActivity<View, TLceBean, TRefreshActivityView, TRefreshActivityPresenter> implements TRefreshActivityView {

    //TODO 改变Activity,设置参数
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TRefreshActivity.class);
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
