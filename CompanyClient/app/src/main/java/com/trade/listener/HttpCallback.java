package com.trade.listener;

import com.tamic.novate.Throwable;

/**
 * Created by Stephen Sun on 2018/7/1 0001.
 * Email:suncunx@qq.com
 */

public interface HttpCallback<T> {
    void onSuccess(T t);

    void onFailure(Throwable e);
}
