package com.architecture.mvp.rv;

import android.support.v7.widget.RecyclerView;

import com.architecture.mvp.lce.BaseLceView;

/**
 * Created by Stephen Sun on 2017/5/22 0022.
 * Email:suncunx@qq.com
 *
 * 此处比较特殊，在Activity使用的时候直接传入POJO类，但是实际上做了转为List的转换
 */

public interface BaseRvView<T> extends BaseLceView<T> {

    RecyclerView getRecyclerView();

    void stopRefresh();
}
