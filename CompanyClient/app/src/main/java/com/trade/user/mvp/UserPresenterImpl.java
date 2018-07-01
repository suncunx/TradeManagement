package com.trade.user.mvp;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.apkfuns.logutils.LogUtils;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.model.AdapterBean;
import com.architecture.mvp.BasePresenterImpl;
import com.architecture.util.BasePreferUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trade.R;
import com.trade.app.AppData;
import com.trade.main.ui.MainActivity;
import com.trade.user.adapter.UserAdapter;
import com.trade.user.model.UserItem;
import com.trade.util.DialogUtil;
import com.trade.util.PreferUtil;
import com.trade.widget.HorizontalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */

public class UserPresenterImpl extends BasePresenterImpl<UserView> implements UserPresenter, BaseQuickAdapter.OnItemClickListener {

    private static final String TOAST_FONT = "设置字体成功！重启app查看效果";
    private int[] resIds = {R.mipmap.ic_user_collect, R.mipmap.ic_user_font, R.mipmap.ic_user_settings, R.mipmap.ic_user_advice};
    private String[] titles = {"清除缓存", "正文字体", "关于我们", "建议反馈"};

    @Override
    public void loadUser() {
        getView().setData(getData());
    }

    private AdapterBean getData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        RecyclerView.ItemDecoration decoration = new HorizontalDecoration(context, R.drawable.divider_gray_light);
        LogUtils.d(decoration);
        AdapterBean adapterBean = new AdapterBean(layoutManager, getAdapter(), decoration);
        return adapterBean;
    }

    private RecyclerView.Adapter getAdapter() {
        List<UserItem> list = new ArrayList<>();

        for (int i = 0; i < titles.length; i++) {
            list.add(new UserItem(resIds[i], titles[i]));
        }

        UserAdapter adapter = new UserAdapter(R.layout.item_list_user);
        adapter.setNewData(list);
        //        adapter.setOnItemClickListener(UserListeners.getItemClickListener(context));
        adapter.setOnItemClickListener(this);

        return adapter;
    }

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
    }

    private void showFontDialog() {
        final MainActivity activity = (MainActivity) getView().getUserActivity();
        DialogUtil.buildFontDialog(activity)
                .itemsCallbackSingleChoice(PreferUtil.getInstance().getGlobalFont() - 1,
                        new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {

                                PreferUtil.getInstance().setGlobalFont(which + 1);
                                activity.getResources();
                                ToastUtils.showShort(TOAST_FONT);
                                return true;
                            }
                        })
                .show();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                showClearDataDialog();
                break;
            case 1:
                showFontDialog();
                break;
            case 2:
                showAboutUsDialog();
                break;
            case 3:
                showAdviseDialog();
                break;
        }
    }

    private void showAdviseDialog() {
        new MaterialDialog.Builder(getView().getUserActivity())
                .positiveText("确定")
                .negativeText("取消")
                .title("建议反馈")
                .input("请输入建议", null, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {

                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        ToastUtils.showShort("发送成功");
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showAboutUsDialog() {
        new MaterialDialog.Builder(getView().getUserActivity())
                .positiveText("确定")
                .title("关于我们")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .customView(R.layout.content_about_us, true)
                .show();
    }

    private void showClearDataDialog() {
        new MaterialDialog.Builder(getView().getUserActivity())
                .negativeText("取消")
                .positiveText("确定")
                .title("提示")
                .content("确定要清除数据吗")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        PreferUtil.getInstance().setLoginOut();
                        BasePreferUtil.getInstance().setUserId("");
                        AppData.clearAppData(getView().getUserActivity());
//                        context.startActivity(new Intent(context, LoginActivity.class));
//                        ((Activity) context).finish();
                    }
                })
                .show();
    }
}
