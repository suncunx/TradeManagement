package com.architecture.mvp.lce;

import android.content.Context;

import com.architecture.di.components.ApplicationComponent;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * Created by Stephen Sun on 2017/6/11 0011.
 * Email:suncunx@qq.com
 */

public abstract class BaseLcePresenterImpl<V extends BaseLceView> extends MvpBasePresenter<V> {

    protected Context context;
    // 用集合保存？
//    protected Subscription subscription;

    @Override
    public void attachView(V view) {
        super.attachView(view);
        context = view.getApplicationComponent().context();
        initializeInjector(view.getApplicationComponent());
    }
    protected abstract void initializeInjector(ApplicationComponent applicationComponent);

    // 在这里取消线程的操作
//    @Override
//    public void detachView(boolean retainInstance) {
//        super.detachView(retainInstance);
//        Log.d("BaseLcePresenterImpl", "detachView: subscription = " + subscription);
//        if (subscription != null && !subscription.isUnsubscribed()) {
//            subscription.unsubscribe();
//        }
//    }
}
