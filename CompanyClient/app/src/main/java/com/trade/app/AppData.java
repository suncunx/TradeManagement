package com.trade.app;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

/**
 * Created by XuPin on 2017/2/24.
 */
public class AppData {
    private static final String TAG = "AppData";

    public static void clearAppData(Context context) {
        clearData(context);
    }

    private static void clearData(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.d(TAG, "Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT");
            ((ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE))
                    .clearApplicationUserData(); // note: it has a return value!
        } else {
            Log.d(TAG, "Build.VERSION.SDK_INT = " + Build.VERSION.SDK_INT);
        }
    }
}
