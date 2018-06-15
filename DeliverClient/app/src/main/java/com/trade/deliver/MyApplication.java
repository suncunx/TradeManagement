package com.trade.deliver;

import android.app.Application;

import com.xuhao.android.libsocket.sdk.OkSocket;

/**
 * Created by Stephen Sun on 2018/5/3 0003.
 * Email:suncunx@qq.com
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferUtil.init(this);
        OkSocket.initialize(this, true);
    }
}
