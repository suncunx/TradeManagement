package com.trade.model;

import java.util.List;

/**
 * Created by Stephen Sun on 2018/4/18 0018.
 * Email:suncunx@qq.com
 */

public class SupplierResultBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : {"suppliers":[{"supplierId":"4","name":"中国移动","phone":"10086","address":"新街口"},{"supplierId":"4","name":"中国移动","phone":"10086","address":"新街口"},{"supplierId":"1","name":"阿里巴巴","phone":"13675234396","address":"南京工程学院"},{"supplierId":"1","name":"阿里巴巴","phone":"13675234396","address":"南京工程学院"}]}
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
        private List<SupplierBean> suppliers;

        public List<SupplierBean> getSuppliers() {
            return suppliers;
        }

        public void setSuppliers(List<SupplierBean> suppliers) {
            this.suppliers = suppliers;
        }

        public static class SupplierBean {
            /**
             * supplierId : 4
             * name : 中国移动
             * phone : 10086
             * address : 新街口
             */

            private String supplierId;
            private String name;
            private String phone;
            private String address;

            public String getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(String supplierId) {
                this.supplierId = supplierId;
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

            @Override
            public String toString() {
                return "SupplierLayoutBean{" +
                        "supplierId='" + supplierId + '\'' +
                        ", name='" + name + '\'' +
                        ", phone='" + phone + '\'' +
                        ", address='" + address + '\'' +
                        '}';
            }
        }
    }
}
