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
    private static final String ACTION_QCHAT_CLEARDATA = "com.dt.qchat.ui.CLEAR_DATA";
    private static final long WAIT_TIME = 30 * 1000;
    private static final int JOBID = 147258;

    public static void clearAppData(Context context) {
        //        DBHelper.getHelper(context).close();
        clearData(context);
    }

    public static PendingIntent getStartMasterControllerPendingIntent(Context context) {
        Intent intent = new Intent("com.bigbangtech.mastercontroller.START_SERVICE");
        //			intent.setAction("com.hoowe.xupinsensorsdk.sensor_polling");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 0, intent, 0);
        return pendingIntent;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void cancelJobscheduler(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context
                .getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(JOBID);

    }

    public static void clearData(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.d(TAG, "Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT");
            ((ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE))
                    .clearApplicationUserData(); // note: it has a return value!
        } else {
            Log.d(TAG, "Build.VERSION.SDK_INT = " + Build.VERSION.SDK_INT);
        }
    }
}
