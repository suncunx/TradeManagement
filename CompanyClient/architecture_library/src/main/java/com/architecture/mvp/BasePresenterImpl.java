package com.architecture.mvp;

import android.content.Context;

import com.architecture.di.components.ApplicationComponent;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

/**
 * Created by Stephen Sun on 2017/6/11 0011.
 * Email:suncunx@qq.com
 */

public abstract class BasePresenterImpl<V extends BaseView> extends MvpBasePresenter<V> {

    // 要不要 activity，是不是所有的实现都会依附于Activity?   还有fragment,View?最终还是在Activity中？
    // 还是不要把，不然每个Presenter都必须要得到一个ActivityModule用来获取Activity实例
//    protected Activity activity;
    protected Context context;

    // 在Activity的onCreate方法中最早调用   在Fragment的onViewCreated中调用
    // 所以在重写此方法时，不能使用还未初始化的对象
    @Override
    public void attachView(V view) {
        super.attachView(view);
        context = view.getApplicationComponent().context();
        initializeInjector(view.getApplicationComponent());
    }

    protected abstract void initializeInjector(ApplicationComponent applicationComponent);

//    public Activity getActivity() {
//        return activity;
//    }

//    @Inject
//    public void setActivity(Activity activity) {
//        this.activity = activity;
//    }

    // 在这里取消线程的操作 // 在Activity的onDestroy方法中调用   在Fragment的onDestroyView中调用
    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
