package com.trade.util;

import com.apkfuns.logutils.LogUtils;
import com.architecture.util.BaseConstants;
import com.architecture.util.BasePreferUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.trade.goods.save.mvp.GoodsSavePresenter;
import com.trade.model.GoodsResultBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Stephen Sun on 2017/8/29 0029.
 * Email:suncunx@qq.com
 */

public class UploadHelper {
    private static final String RELEASE_URL = BaseConstants.BASE_URL + "save.goods";
    private static final String RELEASE_URL_UPDATE = BaseConstants.BASE_URL + "update.goods";
    private static final String IMAGE = "image";
    private static final String IMAGES = "image[]";
    private static final String SOCKET_TIME_OUT = "连接超时，请检查你的网络设置";
    private static final String RELEASE_SUCCESS = "添加成功！";
    private static final String RELEASE_SUCCESS_UPDATE = "修改成功！";
//todo 看点
    public static void release(String path, Map<String, String> params, final GoodsSavePresenter presenter) throws UnsupportedEncodingException {
        PostFormBuilder builder = OkHttpUtils.post()
                .url(RELEASE_URL)
                .addHeader("userId", BasePreferUtil.getInstance().getUserId())
                .addHeader("supplierId", params.get("supplierId"))
                .addHeader("name", URLEncoder.encode(params.get("name"), "utf-8"))
                .addHeader("unit", URLEncoder.encode(params.get("unit"), "utf-8"))
                .addHeader("inUnitPrice", params.get("inUnitPrice"))
                .addHeader("outUnitPrice", params.get("outUnitPrice"))
                .addHeader("image", params.get("image"))
                .params(params);
        if (path != null && !path.equals("")) {
            File file = new File(path);
            builder.addFile(IMAGE, file.getName(), file);
        }
        builder.build()
                .execute(new Callback<Integer>() {
                    @Override
                    public Integer parseNetworkResponse(Response response, int id) throws Exception {
                        return response.code();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        presenter.dismissProgressDialog();
                        if (e instanceof SocketTimeoutException) {
                            ToastUtils.showShort(SOCKET_TIME_OUT);
                        } else {
                            ToastUtils.showShort("网络错误！");
                        }
                    }

                    @Override
                    public void onResponse(Integer response, int id) {
                        presenter.dismissProgressDialog();
                        if (response == 200) {
                            ToastUtils.showShort(RELEASE_SUCCESS);
                        }
                    }
                });
    }
    public static void release(String path, Map<String, String> params, final GoodsSavePresenter presenter, GoodsResultBean.ResultBean.GoodsBean goodsBean) throws UnsupportedEncodingException {
        PostFormBuilder builder = OkHttpUtils.post()
                .url(RELEASE_URL_UPDATE)
                .addHeader("userId", BasePreferUtil.getInstance().getUserId())
                .addHeader("supplierId", params.get("supplierId"))
                .addHeader("goodsId", goodsBean.getGoodsId())
                .addHeader("name", URLEncoder.encode(params.get("name"), "utf-8"))
                .addHeader("unit", URLEncoder.encode(params.get("unit"), "utf-8"))
                .addHeader("inUnitPrice", params.get("inUnitPrice"))
                .addHeader("outUnitPrice", params.get("outUnitPrice"))
                .addHeader("image", URLEncoder.encode(params.get("image"), "utf-8"))
                .params(params);
        if (path != null && !path.equals("")) {
            File file = new File(path);
            builder.addFile(IMAGE, URLEncoder.encode(file.getName(), "utf-8"), file);
        }
        builder.build()
                .execute(new Callback<Integer>() {
                    @Override
                    public Integer parseNetworkResponse(Response response, int id) throws Exception {
                        return response.code();
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        presenter.dismissProgressDialog();
                        if (e instanceof SocketTimeoutException) {
                            ToastUtils.showShort(SOCKET_TIME_OUT);
                        } else {
//                            ToastUtils.showShort("网络错误！");
                            LogUtils.e(e.getMessage());
                        }
                    }

                    @Override
                    public void onResponse(Integer response, int id) {
                        presenter.dismissProgressDialog();
                        if (response == 200) {
                            ToastUtils.showShort(RELEASE_SUCCESS_UPDATE);
                        }
                    }
                });
    }
    private static String encodeHeadInfo( String headInfo ) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0, length = headInfo.length(); i < length; i++) {
            char c = headInfo.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                stringBuffer.append( String.format ("\\u%04x", (int)c) );
            } else {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }
}
