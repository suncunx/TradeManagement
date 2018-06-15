package com.architecture.mvp.rv;

import com.architecture.mvp.lce.BaseLcePresenter;

/**
 * Created by Stephen Sun on 2017/5/22 0022.
 * Email:suncunx@qq.com
 */

public interface BaseRvPresenter<V extends BaseRvView> extends BaseLcePresenter<V> {
    void setOnLoadMoreListener(V view);
}
