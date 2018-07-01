package com.trade.goods.presenter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.model.DropMenuBean;
import com.architecture.mvp.lce.BaseNovateLcePresenterImpl;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jph.takephoto.model.TImage;
import com.tamic.novate.Throwable;
import com.trade.BR;
import com.trade.R;
import com.trade.goods.di.DaggerGoodsPresenterComponent;
import com.trade.goods.di.GoodsPresenterModule;
import com.trade.goods.model.GoodsModel;
import com.trade.goods.model.GoodsService;
import com.trade.goods.model.GoodsSaveBean;
import com.trade.goods.view.GoodsSaveView;
import com.trade.listener.HttpCallback;
import com.trade.goods.model.GoodsResultBean;
import com.trade.other.model.SupplierResultBean;
import com.trade.widget.popup.DropItemClickListener;
import com.trade.widget.popup.PopupHelper;
import com.trade.widget.popup.PopupItem;
import com.trade.util.UploadHelper;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
public class GoodsSavePresenterImpl extends BaseNovateLcePresenterImpl<GoodsSaveView, GoodsService> implements GoodsSavePresenter, DropItemClickListener, View.OnClickListener {

    private static final String TAG = "OutBillSavePresenterImpl";
    private static final String HEADER = "选择供应商";
    private static final String IN_RELEASE = "添加商品中...";
    private static final String IN_RELEASE_UPDATE = "修改商品中...";

    private List<String> supplierStrList = new ArrayList<>();
    private List<SupplierResultBean.ResultBean.SupplierBean> supplierList;
    private DropMenuBean dropMenuBean;
    private GoodsSaveBean saveBean;
    private GoodsResultBean.ResultBean.GoodsBean goodsBean;
    private View contentView;
    private String supplierId;

    private MaterialDialog progress;

    @Inject
    GoodsModel goodsModel;

    @Override
    protected void init() {
        saveBean = new GoodsSaveBean("", "", "", "", "", new ChooseImageListener(), GoodsSavePresenterImpl.this);
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.content_goods_save, null, false);
        binding.setVariable(BR.goodsSaveBean, saveBean);
        contentView = binding.getRoot();
    }

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        DaggerGoodsPresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .goodsPresenterModule(new GoodsPresenterModule())
                .build()
                .inject(this);
    }

    @Override
    public void loadSuppliers(final boolean pullToRefresh, final GoodsResultBean.ResultBean.GoodsBean goodsBean) {
        getView().showLoading(pullToRefresh);
        this.goodsBean = goodsBean;
        goodsModel.listSupplier(new HttpCallback<SupplierResultBean>() {
            @Override
            public void onSuccess(SupplierResultBean resultBean) {
                supplierList = resultBean.getResult().getSuppliers();
                supplierStrList.clear();
                for (SupplierResultBean.ResultBean.SupplierBean supplierBean :
                        supplierList) {
                    supplierStrList.add(supplierBean.getName());
                }

                if (goodsBean != null) {
                    saveBean.setName(goodsBean.getName());
                    saveBean.setUnit(goodsBean.getUnit());
                    saveBean.setInUnitPrice(goodsBean.getInUnitPrice());
                    saveBean.setOutUnitPrice(goodsBean.getOutUnitPrice());
                    saveBean.setImage(goodsBean.getImage());
                    supplierStrList.clear();
                    dropMenuBean = PopupHelper.getSimpleDropMenuBean(GoodsSavePresenterImpl.this.context, GoodsSavePresenterImpl.this, goodsBean.getSupplierName(), supplierStrList, contentView);
                    supplierId = goodsBean.getSupplierId();
                } else {
                    dropMenuBean = PopupHelper.getSimpleDropMenuBean(GoodsSavePresenterImpl.this.context, GoodsSavePresenterImpl.this, HEADER, supplierStrList, contentView);
                }
                getView().setData(dropMenuBean);
                getView().showContent();
            }

            @Override
            public void onFailure(Throwable e) {
                if (getView() != null) {
                    getView().showError(e, pullToRefresh);
                }
            }
        });

    }

    // 显示选择的图片
    @Override
    public void showImage(TImage image) {
        saveBean.setImage(image.getCompressPath());
    }

    @Override
    public void onDropItemClick(BaseQuickAdapter adapter, int position, int tabPosition) {
        // 获取点击的商品，然后上传
        PopupItem popupItem = (PopupItem) adapter.getData().get(position);
        int i = 0;
        while (!supplierStrList.get(i).equals(popupItem.getText())) {
            i++;
        }
        supplierId = supplierList.get(i).getSupplierId();
        //        ToastUtils.showShort(supplierId);
    }

    // 添加
    @Override
    public void onClick(View view) {
        String name = saveBean.getName();
        String unit = saveBean.getUnit();
        String inUnitPrice = saveBean.getInUnitPrice();
        String outUnitPrice = saveBean.getOutUnitPrice();
        String image = saveBean.getImage();
        Log.d(TAG, "onClick: imagePath = " + image);
        if (supplierId == null || TextUtils.isEmpty(supplierId.trim())) {
            ToastUtils.showShort("请选择供应商！");
            return;
        }
        if (TextUtils.isEmpty(name.trim())) {
            ToastUtils.showShort("请输入商品名称！");
            return;
        }
        if (TextUtils.isEmpty(unit.trim())) {
            ToastUtils.showShort("请输入商品单位！");
            return;
        }
        if (TextUtils.isEmpty(inUnitPrice.trim())) {
            ToastUtils.showShort("请输入商品进价！");
            return;
        }
        if (TextUtils.isEmpty(outUnitPrice.trim())) {
            ToastUtils.showShort("请输入商品出价！");
            return;
        }
        if (TextUtils.isEmpty(image.trim())) {
            ToastUtils.showShort("请选择商品图片！");
            return;
        }

        saveGoods(name, unit, inUnitPrice, outUnitPrice, image);

    }

    private void saveGoods(String name, String unit, String inUnitPrice, String outUnitPrice, String image) {
        Map<String, String> map = new HashMap<>();
        map.put("supplierId", supplierId);
        map.put("name", name);
        map.put("unit", unit);
        map.put("inUnitPrice", inUnitPrice);
        map.put("outUnitPrice", outUnitPrice);
        //        map.put("image", image == null || image.substring(0, 4).equals("http") ? "" : image.substring(image.lastIndexOf("/") + 1));
        map.put("image", image);
        image = image.length() > 4 && image.substring(0, 4).equals("http") ? "" : image;
        showProgressDialog();
        try {
            if (goodsBean == null) {
                UploadHelper.release(image, map, this);
            } else {
                UploadHelper.release(image, map, this, goodsBean);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void showProgressDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getView().getActivity())
                .content(goodsBean == null ? IN_RELEASE : IN_RELEASE_UPDATE)
                .progress(true, 0);
        progress = builder.cancelable(false)
                .canceledOnTouchOutside(false)
                .show();
    }

    public void dismissProgressDialog() {
        if (progress != null) {
            progress.dismiss();
        }
    }

    private class ChooseImageListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            getView().showMaterialDialog();
        }
    }
}
