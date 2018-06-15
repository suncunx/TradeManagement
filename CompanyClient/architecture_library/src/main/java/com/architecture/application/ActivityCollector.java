package com.architecture.application;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen Sun on 2017/4/6 0006.
 * Email:1243357168@qq.com
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static Activity getFirstAtivity() {
        return activities.get(0);
    }

    public static Activity getLastActivity() {
        return activities.get(activities.size() - 1);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
