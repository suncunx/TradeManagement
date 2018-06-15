package com.architecture.application;

import android.view.View;

import com.architecture.mvp.lce.BaseLceActivity;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

/**
 * Created by Stephen Sun on 2017/7/4 0004.
 * Email:suncunx@qq.com
 */

public abstract class BaseActionBarActivity<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>> extends BaseLceActivity<CV, M, V, P> {
}
