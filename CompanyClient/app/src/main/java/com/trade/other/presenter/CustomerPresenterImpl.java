package com.trade.other.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.architecture.di.components.ApplicationComponent;
import com.architecture.model.AdapterBean;
import com.architecture.mvp.rv.BaseNovateRvPresenterImpl;
import com.architecture.util.BaseConstants;
import com.architecture.util.BasePreferUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.trade.R;
import com.trade.other.model.CustomerResultBean;
import com.trade.model.SimpleResultBean;
import com.trade.other.adapter.CustomerAdapter;
import com.trade.other.di.DaggerOtherPresenterComponent;
import com.trade.other.di.OtherPresenterModule;
import com.trade.other.model.CustomerService;
import com.trade.other.view.CustomerView;
import com.trade.util.PhoneNumberUtil;
import com.trade.widget.HorizontalDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;

import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Stephen Sun on 2017/7/11 0011.
 * Email:suncunx@qq.com
 */

public class CustomerPresenterImpl extends BaseNovateRvPresenterImpl<CustomerView, CustomerService> implements CustomerPresenter, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener, View.OnClickListener {

    @Override
    protected void initializeInjector(ApplicationComponent applicationComponent) {
        DaggerOtherPresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .otherPresenterModule(new OtherPresenterModule())
                .build()
                .inject(this);
    }

    @Override
    public void loadData(final boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
        novate.call(service.getCustomers(), new BaseSubscriber<CustomerResultBean>() {
            @Override
            public void onError(Throwable e) {
                getView().showError(e, pullToRefresh);
            }

            @Override
            public void onNext(CustomerResultBean resultBean) {
                List<CustomerResultBean.ResultBean.CustomerBean> supplierBeans = resultBean.getResult().getCustomers();
                if (supplierBeans.size() == 0) {
                    getView().showError(new IndexOutOfBoundsException(), false);
                    return;
                }
                adapter.setNewData(supplierBeans);
                AdapterBean adapterBean = new AdapterBean(new LinearLayoutManager(CustomerPresenterImpl.this.context), adapter, new HorizontalDecoration(CustomerPresenterImpl.this.context, R.drawable.divider_thin));
                getView().setData(adapterBean);
                getView().showContent();
            }
        });

    }

    @Override
    public View.OnClickListener getClickListener() {
        return this;
    }

    @Override
    protected BaseQuickAdapter createAdapter() {
        return new CustomerAdapter(R.layout.item_list_customer);
    }

    @Override
    public void loadMore() {
        loadMoreEnd(false);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        // 修改、删除
        final CustomerResultBean.ResultBean.CustomerBean customerBean = (CustomerResultBean.ResultBean.CustomerBean) adapter.getData().get(position);
        new MaterialDialog.Builder(getView().getActivity())
                .items("修改", "删除")
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        if (position == 0) {
                            showUpdateDialog(customerBean);
                        } else {
                            showDeleteDialog(customerBean);
                        }
                    }
                })
                .show();
    }

    private void showDeleteDialog(final CustomerResultBean.ResultBean.CustomerBean customerBean) {
        new MaterialDialog.Builder(getView().getActivity())
                .title("提示")
                .content("确定要删除此客户吗")
                .positiveText("确定")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        String customerId = customerBean.getCustomerId();
                        novate.call(service.removeCustomer(customerId), new BaseSubscriber<SimpleResultBean>() {
                            @Override
                            public void onError(Throwable e) {
                                getView().showError(e, false);
                            }

                            @Override
                            public void onNext(SimpleResultBean simpleResultBean) {
                                ToastUtils.showShort(simpleResultBean.getMsg());
                                loadData(true);
                            }
                        });
                        dialog.dismiss();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showUpdateDialog(final CustomerResultBean.ResultBean.CustomerBean customerBean) {
        boolean wrapInScrollView = true;
        MaterialDialog dialog = new MaterialDialog.Builder(getView().getActivity())
                .title("修改客户")
                .customView(R.layout.supplier_update, wrapInScrollView)
                .positiveText("修改")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull final MaterialDialog dialog, @NonNull DialogAction which) {
                        View content = dialog.getCustomView();
                        EditText nameEdit = content.findViewById(R.id.edit_name);
                        EditText phoneEdit = content.findViewById(R.id.edit_phone);
                        EditText addressEdit = content.findViewById(R.id.edit_address);
                        String name = nameEdit.getText().toString();
                        String phone = phoneEdit.getText().toString();
                        String address = addressEdit.getText().toString();
                        if (TextUtils.isEmpty(name)) {
                            ToastUtils.showShort("请输入姓名");
                            return;
                        }
                        if (!PhoneNumberUtil.isValidPhoneNumber(phone)) {
                            ToastUtils.showShort("请输入有效电话");
                            return;
                        }
                        if (TextUtils.isEmpty(address)) {
                            ToastUtils.showShort("请输入地址");
                            return;
                        }
                        try {
                            update(dialog, customerBean.getCustomerId(), name, phone, address);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .build();
        View content = dialog.getCustomView();
        EditText nameEdit = content.findViewById(R.id.edit_name);
        EditText phoneEdit = content.findViewById(R.id.edit_phone);
        EditText addressEdit = content.findViewById(R.id.edit_address);
        nameEdit.setText(customerBean.getName());
        phoneEdit.setText(customerBean.getPhone());
        addressEdit.setText(customerBean.getAddress());
        dialog.show();
    }

    private void update(final MaterialDialog dialog, String customerId, String name, String phone, String address) throws UnsupportedEncodingException {
        PostFormBuilder builder = OkHttpUtils.post()
                .url(BaseConstants.BASE_URL + "update.customer")
                .addHeader("userId", BasePreferUtil.getInstance().getUserId())
                .addHeader("customerId", customerId)
                .addHeader("name", URLEncoder.encode(name, "utf-8"))
                .addHeader("phone", phone)
                .addHeader("address", URLEncoder.encode(address, "utf-8"));
        builder.build()
                .execute(new Callback<Integer>() {
                    @Override
                    public Integer parseNetworkResponse(Response response, int id) throws Exception {
                        return response.code();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        if (e instanceof SocketTimeoutException) {
                            ToastUtils.showShort("连接超时，请检查你的网络设置");
                        } else {
                            ToastUtils.showShort("网络错误！");
                        }
                    }

                    @Override
                    public void onResponse(Integer response, int id) {
                        dialog.dismiss();
                        if (response == 200) {
                            ToastUtils.showShort("修改成功！");
                            loadData(true);
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        boolean wrapInScrollView = true;
        MaterialDialog dialog = new MaterialDialog.Builder(getView().getActivity())
                .title("添加客户")
                .customView(R.layout.supplier_update, wrapInScrollView)
                .positiveText("添加")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull final MaterialDialog dialog, @NonNull DialogAction which) {
                        View content = dialog.getCustomView();
                        EditText nameEdit = content.findViewById(R.id.edit_name);
                        EditText phoneEdit = content.findViewById(R.id.edit_phone);
                        EditText addressEdit = content.findViewById(R.id.edit_address);
                        String name = nameEdit.getText().toString();
                        String phone = phoneEdit.getText().toString();
                        String address = addressEdit.getText().toString();
                        if (TextUtils.isEmpty(name)) {
                            ToastUtils.showShort("请输入姓名");
                            return;
                        }
                        if (!PhoneNumberUtil.isValidPhoneNumber(phone)) {
                            ToastUtils.showShort("请输入有效电话");
                            return;
                        }
                        if (TextUtils.isEmpty(address)) {
                            ToastUtils.showShort("请输入地址");
                            return;
                        }
                        try {
                            save(dialog, name, phone, address);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void save(final MaterialDialog dialog, String name, String phone, String address) throws UnsupportedEncodingException {
        PostFormBuilder builder = OkHttpUtils.post()
                .url(BaseConstants.BASE_URL + "save.customer")
                .addHeader("userId", BasePreferUtil.getInstance().getUserId())
                .addHeader("name", URLEncoder.encode(name, "utf-8"))
                .addHeader("phone", phone)
                .addHeader("address", URLEncoder.encode(address, "utf-8"));
        builder.build()
                .execute(new Callback<Integer>() {
                    @Override
                    public Integer parseNetworkResponse(Response response, int id) throws Exception {
                        return response.code();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        dialog.dismiss();
                        if (e instanceof SocketTimeoutException) {
                            ToastUtils.showShort("连接超时，请检查你的网络设置");
                        } else {
                            ToastUtils.showShort("网络错误！");
                        }
                    }

                    @Override
                    public void onResponse(Integer response, int id) {
                        dialog.dismiss();
                        if (response == 200) {
                            ToastUtils.showShort("添加成功！");
                            loadData(true);
                        }
                    }
                });
    }
}
