package com.architecture.mvp.lce;

import com.architecture.di.components.ApplicationComponent;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

/**
 * Created by Stephen Sun on 2017/6/11 0011.
 * Email:suncunx@qq.com
 * 所有的View继承此类
 */

public interface BaseLceView<T> extends MvpLceView<T> {
    ApplicationComponent getApplicationComponent();
}
