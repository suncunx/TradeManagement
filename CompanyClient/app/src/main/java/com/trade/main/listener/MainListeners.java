package com.trade.main.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.trade.home.ui.InBillSaveActivity;
import com.trade.home.ui.OutBillSaveActivity;
import com.trade.main.presenter.MainPresenter;
import com.trade.util.Constants;
import com.trade.util.PreferUtil;

/**
 * Created by Stephen Sun on 2017/7/10 0010.
 * Email:suncunx@qq.com
 */

public class MainListeners {

    public static View.OnClickListener getLeftClickListener(final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = CityActivity.getCallingIntent(context);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
            }
        };
    }
    public static View.OnClickListener getTitleClickListener(final Context context, final MainPresenter presenter) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(context)
                        .items(Constants.OUT_BILL, Constants.IN_BILL)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                if (position == 0) {
                                    // 查看出货账单
                                    PreferUtil.getInstance().setIsOutBill(true);
                                    presenter.onTitleChange(true);
                                } else {
                                    // 查看进货账单
                                    PreferUtil.getInstance().setIsOutBill(false);
                                    presenter.onTitleChange(false);
                                }
                            }
                        })
                        .show();
            }
        };
    }
    public static View.OnClickListener getRightClickListener(final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(context)
                        .items(Constants.OUT, Constants.IN)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                if (position == 0) {
                                    Intent intent = OutBillSaveActivity.getCallingIntent(context);
                                    context.startActivity(intent);
                                } else {
                                    Intent intent = InBillSaveActivity.getCallingIntent(context);
                                    context.startActivity(intent);
                                }
                            }
                        })
                        .show();
            }
        };
    }
}
