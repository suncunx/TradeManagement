package com.trade.other.model;

import com.trade.model.SimpleResultBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Stephen Sun on 2017/8/16 0016.
 * Email:suncunx@qq.com
 */

public interface CustomerService {
    @POST("list.customer")
    Observable<CustomerResultBean> getCustomers();

    @FormUrlEncoded
    @POST("save.customer")
    Observable<SimpleResultBean> saveCustomer(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("update.customer")
    Observable<SimpleResultBean> updateCustomer(@Field("customerId") String customerId, @Field("name") String name, @Field("phone") String phone, @Field("address") String address );

    @FormUrlEncoded
    @POST("remove.customer")
    Observable<SimpleResultBean> removeCustomer(@Field("customerId") String customerId);
}
