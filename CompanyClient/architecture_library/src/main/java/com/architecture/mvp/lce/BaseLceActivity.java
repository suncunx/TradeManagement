package com.architecture.mvp.lce;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.architecture.application.ActivityCollector;
import com.architecture.application.AndroidApplication;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.di.modules.ActivityModule;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import javax.inject.Inject;

/**
 * 继承自MvpLceActivity的基类 , 已经进行了依赖注入
 * CV: 布局的根View; M: Activity需要用到的POJO类 , 体现在setData方法的参数;
 * V: 继承接口MvpLceView的接口 , 包含对应Activity需要做的操作
 * P: 继承接口MvpPresenter的接口
 * 继承了这个类的Activity需要实现V接口
 * Created by Stephen Sun on 2017/1/15 0015.
 * Email:suncunx@qq.com
 * <p>
 *  未做ActionBar的封装
 *
 *  此Activity与BaseActivity是没有继承关系的，如果继承则需要将BaseLceActivity写成MvpLceActivity的形式
 *  但是ViewStateActivity继承了MvpLceActivity，也就是说如果我们使用ViewStateActivity还需要重写他，为了避免
 *  产生连环错误，直接将BaseLceActivity与BaseActivity分割开，并且完全可以同时用ActivityCollector管理
 */

public abstract class BaseLceActivity<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends MvpLceActivity<CV, M, V, P> implements BaseLceView<M> {

    protected ViewDataBinding viewDataBinding;

    // 当子类调用super.onCreate方法时表示依赖注入已经初始化完毕，所以可以直接在onCreate方法中调用依赖的对象
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //先注入再创建Presenter , 调用 createPresenter 方法
        initializeInjector();
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this); // 加入Activity列表中
        initViewDataBinding();

    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    public abstract void initializeInjector();
    protected void init(){}

    private void initViewDataBinding() {
        viewDataBinding = createViewDataBinding();
    }

    public abstract ViewDataBinding createViewDataBinding();

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

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    public ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     */
    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
