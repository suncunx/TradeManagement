package com.architecture.template.base.toolbar;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.architecture.R;
import com.architecture.mvp.BaseToolbarActivity;
import com.architecture.template.base.toolbar.mvp.TToolbarPresenter;
import com.architecture.template.base.toolbar.mvp.TToolbarView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TToolbarActivity extends BaseToolbarActivity<TToolbarView, TToolbarPresenter> implements TToolbarView {
    //TODO 改变Activity,设置参数
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TToolbarActivity.class);
    }
    @Override
    public void initializeInjector() {
        //TODO 初始化Component
    }

    @Override
    protected String createTitle() {
        //TODO 中间标题
        return null;
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        //TODO 修改布局文件
        return DataBindingUtil.setContentView(this, R.layout.template);
    }
}
