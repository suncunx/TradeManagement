package com.trade.model;

import java.util.List;

/**
 * Created by Stephen Sun on 2018/4/18 0018.
 * Email:suncunx@qq.com
 */

public class CustomerResultBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : {"customers":[{"customerId":"2","name":"zhang","phone":"10010","address":"suzhou"}]}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<CustomerBean> customers;

        public List<CustomerBean> getCustomers() {
            return customers;
        }

        public void setCustomers(List<CustomerBean> customers) {
            this.customers = customers;
        }

        public static class CustomerBean {
            /**
             * customerId : 2
             * name : zhang
             * phone : 10010
             * address : suzhou
             */

            private String customerId;
            private String name;
            private String phone;
            private String address;

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
