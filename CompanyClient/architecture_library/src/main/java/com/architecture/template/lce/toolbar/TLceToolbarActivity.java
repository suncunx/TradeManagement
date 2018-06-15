package com.architecture.template.lce.toolbar;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.architecture.R;
import com.architecture.mvp.lce.BaseLceToolbarActivity;
import com.architecture.template.lce.activity.TLceActivity;
import com.architecture.template.lce.toolbar.model.TLceToolbarBean;
import com.architecture.template.lce.toolbar.mvp.TLceToolbarActivityPresenter;
import com.architecture.template.lce.toolbar.mvp.TLceToolbarActivityView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TLceToolbarActivity extends BaseLceToolbarActivity<View, TLceToolbarBean, TLceToolbarActivityView, TLceToolbarActivityPresenter> implements TLceToolbarActivityView {

    //TODO 改变Activity,设置参数
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TLceToolbarActivity.class);
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
    public void setData(TLceToolbarBean data) {
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
