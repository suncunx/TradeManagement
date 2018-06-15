package com.architecture.mvp;

import com.architecture.di.components.ApplicationComponent;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by Stephen Sun on 2017/6/11 0011.
 * Email:suncunx@qq.com
 * 所有的View继承此类
 * <p>
 * // 不一定是所有的View都需要ActivityModule的（Activity对象），所以这里不用，而且用了，在Presenter里面也无效，不知道为什么
 * //现在知道了，是因为里面的ClickListener不在提供给Activity的Presenter实例当中
 * //    ActivityModule getActivityModule();
 */

public interface BaseView extends MvpView {
    ApplicationComponent getApplicationComponent();
}
