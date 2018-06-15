package com.architecture.template.base.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.architecture.R;
import com.architecture.mvp.BaseActivity;
import com.architecture.template.base.activity.mvp.TActivityPresenter;
import com.architecture.template.base.activity.mvp.TActivityView;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class TActivity extends BaseActivity<TActivityView, TActivityPresenter> implements TActivityView {

    //TODO 改变Activity,设置参数
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TActivity.class);
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
}
