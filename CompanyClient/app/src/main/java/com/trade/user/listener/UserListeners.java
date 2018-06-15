package com.trade.user.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trade.app.AppData;
import com.trade.user.login.LoginActivity;
import com.trade.user.model.UserItem;
import com.trade.util.PreferUtil;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class UserListeners {

    private Context context;

    public UserListeners(Context context) {
        this.context = context;
    }

    public static BaseQuickAdapter.OnItemClickListener getItemClickListener(final Context context) {
        return new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShort(((UserItem) adapter.getItem(position)).getTitle());
            }
        };
    }

    public void onUserLoginClick() {
        boolean hasLogin = PreferUtil.getInstance().isLogin();
        if (!hasLogin) {
            context.startActivity(new Intent(context, LoginActivity.class));
        } else {
            new MaterialDialog.Builder(context)
                    .negativeText("取消")
                    .positiveText("确定")
                    .title("提示")
                    .content("确定要退出登录吗")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                            PreferUtil.getInstance().setLoginOut();
//                            BasePreferUtil.getInstance().setUserId("");
                            AppData.clearAppData(context);
                            context.startActivity(new Intent(context, LoginActivity.class));
                            ((Activity) context).finish();
                        }
                    })
                    .show();
        }
    }
}
