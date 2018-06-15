package com.architecture.mvp;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.architecture.R;
import com.architecture.application.ActivityCollector;
import com.architecture.application.AndroidApplication;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.di.modules.ActivityModule;
import com.blankj.utilcode.util.StringUtils;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import javax.inject.Inject;

/**
 * Created by Stephen Sun on 2017/7/12 0012.
 * Email:suncunx@qq.com
 */

public abstract class BaseToolbarActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> implements BaseView {

    protected ViewDataBinding viewDataBinding;
    private TextView title;
    private ImageView back;

    private TextView action;

    protected void setTitle(String msg) {
        if (title != null && !StringUtils.isEmpty(msg)) {
            title.setText(msg);
        }
    }

    protected void setAction(String text) {
        if (action != null && !StringUtils.isEmpty(text)) {
            LogUtils.d(text);
            action.setText(text);
        }
    }

    protected abstract String createTitle();

    /**
     * sometime you want to define back event
     */
    protected void setBackBtn() {
        if (back != null) {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } else {
            LogUtils.e("back is null ,please check out");
        }

    }

    private void setBackClickListener(View.OnClickListener l) {
        if (l == null) {
            setBackBtn();
            return;
        }
        if (back != null) {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(l);
        } else {
            LogUtils.e("back is null ,please check out");
        }

    }

    // 重写此方法为左边的View设置监听接口
    protected View.OnClickListener createBackClickListener() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        // 经测试在代码里直接声明透明状态栏更有效
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
        //                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //        }
        ActivityCollector.addActivity(this);
        initViewDataBinding();
        initToolbar();

        init(); // 设置返回事件
    }

    private void initViewDataBinding() {
        viewDataBinding = createViewDataBinding();
        if (viewDataBinding == null) {
            throw new NullPointerException("ViewDataBinding is null,please check the layout file includes <layout> tag");
        }
    }

    public abstract ViewDataBinding createViewDataBinding();

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) viewDataBinding.getRoot().findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            // Enable the Up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
        back = (ImageView) viewDataBinding.getRoot().findViewById(R.id.iv_back);
        title = (TextView) viewDataBinding.getRoot().findViewById(R.id.tv_title);
        action = viewDataBinding.getRoot().findViewById(R.id.action);
        setTitle(createTitle());

        setBackClickListener(createBackClickListener());

        setRightClickListener();

    }

    private void setRightClickListener() {
        if (action != null) {
            action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickRight(view);
                }
            });
        }
    }

    protected void onClickRight(View view) {
    }

    public abstract void initializeInjector();

    protected void init() {
    }

    @Inject
    @Override
    public void setPresenter(@NonNull P presenter) {
        super.setPresenter(presenter);
    }

    @NonNull
    @Override
    public P createPresenter() {
        return getPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected void addFragmentToStack(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
