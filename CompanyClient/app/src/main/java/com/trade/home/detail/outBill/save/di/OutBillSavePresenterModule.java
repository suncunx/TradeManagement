package com.trade.home.detail.outBill.save.di;

import android.util.Log;

import com.architecture.di.PerActivity;
import com.architecture.util.BasePreferUtil;
import com.tamic.novate.Novate;

import java.util.HashMap;
import java.util.Map;

import dagger.Module;
import dagger.Provides;

import static com.tamic.novate.Novate.TAG;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */

@Module
public class OutBillSavePresenterModule {
    @PerActivity
    @Provides
    Novate provideOutBillSavePresenterNovate(Novate.Builder builder) {
        Log.d(TAG, "OutBillSavePresenterModule: userId = " + BasePreferUtil.getInstance().getUserId());
        Map<String, Object> header = new HashMap<>();
        header.put("userId", BasePreferUtil.getInstance().getUserId());
        builder.addHeader(header);
        return builder.build();
    }
}
