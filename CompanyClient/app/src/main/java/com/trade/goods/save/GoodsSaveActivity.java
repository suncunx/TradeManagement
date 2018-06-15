package com.trade.goods.save;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.apkfuns.logutils.LogUtils;
import com.architecture.model.DropMenuBean;
import com.blankj.utilcode.util.ToastUtils;
import com.jph.takephoto.app.TakePhotoLceActivity;
import com.jph.takephoto.model.TResult;
import com.trade.BR;
import com.trade.R;
import com.trade.goods.save.di.DaggerGoodsSaveComponent;
import com.trade.goods.save.di.GoodsSaveModule;
import com.trade.goods.save.mvp.GoodsSavePresenter;
import com.trade.goods.save.mvp.GoodsSaveView;
import com.trade.model.GoodsResultBean;
import com.trade.util.ApiManager;
import com.trade.util.Constants;
import com.trade.util.CustomHelper;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class GoodsSaveActivity extends TakePhotoLceActivity<LinearLayout, DropMenuBean, GoodsSaveView, GoodsSavePresenter> implements GoodsSaveView {

    private static final String TAG = "GoodsSaveActivity";
    private static final String TITLE = "新的商品";
    private static final String TITLE_UPDATE = "修改商品";
    private CustomHelper customHelper;
    private static final int LIMIT = 1;
    private GoodsResultBean.ResultBean.GoodsBean goodsBean;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, GoodsSaveActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
    public static Intent getCallingIntent(Context context, GoodsResultBean.ResultBean.GoodsBean goodsBean) {
        Intent intent = new Intent(context, GoodsSaveActivity.class);
        intent.putExtra("goodsBean", goodsBean);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    public void init() {
        if (customHelper == null) {
            customHelper = CustomHelper.of(viewDataBinding.getRoot());
            loadData(false);
        }
    }

    @Override
    public void initializeInjector() {
        DaggerGoodsSaveComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .goodsSaveModule(new GoodsSaveModule())
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        goodsBean = (GoodsResultBean.ResultBean.GoodsBean) getIntent().getSerializableExtra("goodsBean");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String createTitle() {
        return goodsBean == null ? TITLE : TITLE_UPDATE;
    }

    @Override
    public ViewDataBinding createViewDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_goods_save);
    }

    @Override
    public void setData(DropMenuBean data) {
        LogUtils.d(data);
        viewDataBinding.setVariable(BR.dropMenuBean, data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadSuppliers(pullToRefresh, goodsBean);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        presenter.showImage(result.getImage());
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        ToastUtils.showShort("取消选择");
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return ApiManager.CONNECT_TIME_OUT;
    }

    @Override
    public void showMaterialDialog() {
        new MaterialDialog.Builder(this)
                .items(Constants.ACTION_CAMERA, Constants.ACTION_GALLERY)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        if (position == 0) {
                            customHelper.onClick(getTakePhoto(), true, LIMIT);
                        } else {
                            // 从相册选择照片
                            customHelper.onClick(getTakePhoto(), false, LIMIT);
                        }
                    }
                })
                .show();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
