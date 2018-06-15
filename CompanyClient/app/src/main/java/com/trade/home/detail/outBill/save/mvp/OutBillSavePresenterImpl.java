package com.trade.home.detail.outBill.save.mvp;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.model.DropMenuBean;
import com.architecture.mvp.lce.BaseNovateLcePresenterImpl;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.trade.BR;
import com.trade.R;
import com.trade.home.detail.outBill.save.di.DaggerOutBillSavePresenterComponent;
import com.trade.home.detail.outBill.save.di.OutBillSavePresenterModule;
import com.trade.home.detail.outBill.save.model.OutBillInfoResultBean;
import com.trade.home.detail.outBill.save.model.OutBillSaveBean;
import com.trade.home.detail.outBill.save.model.OutBillSaveService;
import com.trade.model.OutBillResultBean;
import com.trade.model.SimpleResultBean;
import com.trade.popup.DropItemClickListener;
import com.trade.popup.PopupHelper;
import com.trade.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Stephen Sun on 2017/7/5 0005.
 * Email:suncunx@qq.com
 */
public class OutBillSavePresenterImpl extends BaseNovateLcePresenterImpl<OutBillSaveView, OutBillSaveService> implements OutBillSavePresenter, DropItemClickListener, View.OnClickListener {

    private static final String TAG = "OutBillSavePresenter";

    private String[] payStatuses = {"未付款", "已付款"};

    private static final String HEADER1 = "商品";
    private static final String HEADER2 = "客户";
//    private static final String HEADER3 = "送货员";
    private static final String HEADER4 = "付款状态";

    private List<OutBillInfoResultBean.ResultBean.RepertoryBean> repertorys;
    private List<OutBillInfoResultBean.ResultBean.CustomerBean> customers;
//    private List<OutBillInfoResultBean.ResultBean.DeliverBean> delivers;

    private List<String> headers = new ArrayList<>();
    private List<String> repertoryStrList = new ArrayList<>();
    private List<String> customerStrList = new ArrayList<>();
//    private List<String> deliverStrList = new ArrayList<>();
    private List<String> payStatusList = Arrays.asList(payStatuses);
    private List<List<String>> data = new ArrayList<>();
    private DropMenuBean dropMenuBean;
    private OutBillSaveBean saveBean;
    private View contentView;

    private String goodsId;
    private String customerId;
    private String payStatus;
//    private String deliverId;

    private int maxGoodsCount;

    private OutBillResultBean.ResultBean.OutBillBean outBillBean;
    private int goodsPosition = -1;

    @Override
    protected void init() {
        headers.add(HEADER1);
        headers.add(HEADER2);
//        headers.add(HEADER3);
        headers.add(HEADER4);

        saveBean = new OutBillSaveBean("", "", OutBillSavePresenterImpl.this);
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.content_out_bill_save, null, false);
        binding.setVariable(BR.outBillSaveBean, saveBean);
        contentView = binding.getRoot();
    }

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        DaggerOutBillSavePresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .outBillSavePresenterModule(new OutBillSavePresenterModule())
                .build()
                .inject(this);
    }

    @Override
    public void loadGoods(final boolean pullToRefresh, final OutBillResultBean.ResultBean.OutBillBean outBillBean) {
        getView().showLoading(pullToRefresh);
        this.outBillBean = outBillBean;
        novate.call(service.getInfo(), new BaseSubscriber<OutBillInfoResultBean>() {
            @Override
            public void onError(Throwable e) {
                getView().showError(e, pullToRefresh);
            }

            @Override
            public void onNext(OutBillInfoResultBean resultBean) {
                initInfos(resultBean);

                if (outBillBean == null) {
                    dropMenuBean = PopupHelper.getSimpleDropMenuBean(OutBillSavePresenterImpl.this.context, OutBillSavePresenterImpl.this, headers, data, contentView);
                } else {
                    headers.clear();
                    headers.add(outBillBean.getGoodsName());
                    headers.add(outBillBean.getCustomerName());
//                    headers.add(outBillBean.getDeliverMan() == null || outBillBean.getDeliverMan().equals("") ? HEADER3 : outBillBean.getDeliverMan());
                    headers.add(outBillBean.getPayStatus().equals("0") ? payStatuses[0] : payStatuses[1]);

                    goodsId = outBillBean.getGoodsId();
                    customerId = outBillBean.getCustomerId();
                    payStatus = outBillBean.getPayStatus();
//                    deliverId = outBillBean.getDeliverId();

                    saveBean.setGoodsCount(outBillBean.getGoodsCount());
                    for (OutBillInfoResultBean.ResultBean.RepertoryBean bean :
                            repertorys) {
                        if (bean.getGoodsId().equals(outBillBean.getGoodsId())) {
                            maxGoodsCount = Integer.parseInt(bean.getGoodsCount()) + Integer.parseInt(outBillBean.getGoodsCount());
                            saveBean.setRepertoryGoodsCount(maxGoodsCount + "");
                            break;
                        }
                    }
//                    repertorys.clear();
                    repertoryStrList.clear();
                    dropMenuBean = PopupHelper.getSimpleDropMenuBean(OutBillSavePresenterImpl.this.context, OutBillSavePresenterImpl.this, headers, data, contentView);
                }

                getView().setData(dropMenuBean);
                getView().showContent();
            }
        });
    }

    private void initInfos(OutBillInfoResultBean resultBean) {
        repertorys = resultBean.getResult().getRepertorys();
        customers = resultBean.getResult().getCustomers();
//        delivers = resultBean.getResult().getDelivers();
        for (OutBillInfoResultBean.ResultBean.RepertoryBean repertoryBean :
                repertorys) {
            repertoryStrList.add(repertoryBean.getGoodsName() + "（" + repertoryBean.getGoodsUnit() + "）");
        }
        for (OutBillInfoResultBean.ResultBean.CustomerBean customerBean :
                customers) {
            customerStrList.add(customerBean.getName());
        }
//        for (OutBillInfoResultBean.ResultBean.DeliverBean deliverBean :
//                delivers) {
//            deliverStrList.add(deliverBean.getDeliverMan());
//        }
        data.clear();
        data.add(repertoryStrList);
        data.add(customerStrList);
//        data.add(deliverStrList);
        data.add(payStatusList);
    }

    @Override
    public void onDropItemClick(BaseQuickAdapter adapter, int position, int tabPosition) {
        // 获取点击的商品，然后上传
        switch (tabPosition) {
            case 0:
                goodsPosition = position;
                goodsId = repertorys.get(position).getGoodsId();
                if (outBillBean == null) {
                    maxGoodsCount = Integer.parseInt(repertorys.get(position).getGoodsCount());
                } else {
                    maxGoodsCount = Integer.parseInt(repertorys.get(position).getGoodsCount()) + Integer.parseInt(outBillBean.getGoodsCount());
                }
                saveBean.setRepertoryGoodsCount(maxGoodsCount + "");
                break;
            case 1:
                customerId = customers.get(position).getCustomerId();
                break;
//            case 2:
//                deliverId = delivers.get(position).getDeliverId();
//                break;
            case 2:
                payStatus = payStatusList.get(position);
                break;
        }
        Log.d(TAG, "onDropItemClick: goodsId = " + goodsId + " customerId = " + customerId
                 + " payStatus = " + payStatus);
    }

    // 添加
    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(goodsId)) {
            ToastUtils.showShort("请选择商品！");
            return;
        }
        if (TextUtils.isEmpty(customerId)) {
            ToastUtils.showShort("请选择客户！");
            return;
        }
        if (TextUtils.isEmpty(payStatus)) {
            ToastUtils.showShort("请选择付款状态！");
            return;
        }
        if (TextUtils.isEmpty(saveBean.getGoodsCount().trim())) {
            ToastUtils.showShort("请输入商品数量！");
            return;
        }
        if (Integer.parseInt(saveBean.getGoodsCount()) > maxGoodsCount) {
            ToastUtils.showShort("库存不足！");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("goodsId", goodsId);
        map.put("goodsCount", saveBean.getGoodsCount());
        map.put("customerId", customerId);
        map.put("payStatus", payStatus.trim().equals(payStatuses[0]) ? "0" : "1");
//        map.put("deliverId", deliverId == null ? "" : deliverId); // 送货员可以不选，订单为未发货状态
        map.put("deliverId", "1"); // 送货员不选，订单为未发货状态
        LogUtils.d(map);

        if (outBillBean == null) {
            saveBillBean(map);
        } else {
            map.put("outBillId", outBillBean.getOutBillId());
            String deliverStatus = outBillBean.getDeliverStatus();
            if (deliverStatus.equals(Constants.DELIVER_NOT)) {
                map.put("deliverStatus", Constants.DELIVER_NOT_CODE + "");
            } else if(deliverStatus.equals(Constants.DELIVER_ING)) {
                map.put("deliverStatus", Constants.DELIVER_ING_CODE + "");
            } else {
                map.put("deliverStatus", Constants.DELIVER_FINISH_CODE + "");
            }
            updateBillBean(map);
        }

    }

    private void saveBillBean(final Map<String, String> map) {
        novate.call(service.saveOutBill(map), new BaseSubscriber<SimpleResultBean>() {
            @Override
            public void onError(Throwable e) {
                getView().showError(e, false);
            }

            @Override
            public void onNext(SimpleResultBean simpleResultBean) {
                ToastUtils.showShort(simpleResultBean.getMsg());
                maxGoodsCount -= Integer.parseInt(map.get("goodsCount"));
                repertorys.get(goodsPosition).setGoodsCount(maxGoodsCount + "");
                saveBean.setRepertoryGoodsCount(maxGoodsCount + "");
                //                getView().finishActivity();
            }
        });
    }

    private void updateBillBean(Map<String, String> map) {
        novate.call(service.updateOutBill(map), new BaseSubscriber<SimpleResultBean>() {
            @Override
            public void onError(Throwable e) {
                getView().showError(e, false);
            }

            @Override
            public void onNext(SimpleResultBean simpleResultBean) {
                ToastUtils.showShort(simpleResultBean.getMsg());
                getView().finishActivity();
            }
        });
    }
}
