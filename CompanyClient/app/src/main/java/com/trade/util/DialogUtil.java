package com.trade.util;

import android.app.Activity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.trade.R;

/**
 * Created by Stephen Sun on 2017/7/24 0024.
 * Email:suncunx@qq.com
 */

public class DialogUtil {

    private static final String FONT_SELECT = "选择字体";

    public static MaterialDialog.Builder buildFontDialog(final Activity activity) {
        final String[] fonts = activity.getResources().getStringArray(R.array.font_size);
        return new MaterialDialog.Builder(activity)
                .title(FONT_SELECT)
                .items(fonts)
                .negativeText(Constants.CANCEL)
                .positiveText(Constants.ENSURE);
    }
}
