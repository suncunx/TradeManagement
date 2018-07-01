package com.trade.login.di;

import com.architecture.di.PerActivity;
import com.tamic.novate.Novate;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

@Module
public class LoginPresenterModule {
    @PerActivity
    @Provides
    Novate provideNovate(Novate.Builder builder) {
//        Log.d(TAG, "LoginPresenterModule: userId = " + BasePreferUtil.getInstance().getUserId());
//        Map<String, Object> header = new HashMap<>();
//        header.put("userId", BasePreferUtil.getInstance().getUserId());
//        builder.addHeader(header);
        return builder.build();
    }

}
