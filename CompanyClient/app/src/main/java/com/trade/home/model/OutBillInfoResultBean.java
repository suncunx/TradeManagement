package com.trade.home.model;

import java.util.List;

/**
 * Created by Stephen Sun on 2018/4/24 0024.
 * Email:suncunx@qq.com
 */

public class OutBillInfoResultBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : {"delivers":[{"deliverId":"2","deliverMan":"马化腾","deliverPhone":"10086","deliverManStatus":"0"}],"customers":[{"customerId":"2","name":"zhang","phone":"10010","address":"suzhou"}],"repertorys":[{"repertoryId":"2","goodsId":"2","goodsName":"1","goodsUnit":"2","goodsCount":"20","goodsImage":"6"},{"repertoryId":"3","goodsId":"1","goodsName":"巧克力","goodsUnit":"箱","goodsCount":"14","goodsImage":"2"}]}
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
        private List<DeliverBean> delivers;
        private List<CustomerBean> customers;
        private List<RepertoryBean> repertorys;

        public List<DeliverBean> getDelivers() {
            return delivers;
        }

        public void setDelivers(List<DeliverBean> delivers) {
            this.delivers = delivers;
        }

        public List<CustomerBean> getCustomers() {
            return customers;
        }

        public void setCustomers(List<CustomerBean> customers) {
            this.customers = customers;
        }

        public List<RepertoryBean> getRepertorys() {
            return repertorys;
        }

        public void setRepertorys(List<RepertoryBean> repertorys) {
            this.repertorys = repertorys;
        }

        public static class DeliverBean {
            /**
             * deliverId : 2
             * deliverMan : 马化腾
             * deliverPhone : 10086
             * deliverManStatus : 0
             */

            private String deliverId;
            private String deliverMan;
            private String deliverPhone;
            private String deliverManStatus;

            public String getDeliverId() {
                return deliverId;
            }

            public void setDeliverId(String deliverId) {
                this.deliverId = deliverId;
            }

            public String getDeliverMan() {
                return deliverMan;
            }

            public void setDeliverMan(String deliverMan) {
                this.deliverMan = deliverMan;
            }

            public String getDeliverPhone() {
                return deliverPhone;
            }

            public void setDeliverPhone(String deliverPhone) {
                this.deliverPhone = deliverPhone;
            }

            public String getDeliverManStatus() {
                return deliverManStatus;
            }

            public void setDeliverManStatus(String deliverManStatus) {
                this.deliverManStatus = deliverManStatus;
            }
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

        public static class RepertoryBean {
            /**
             * repertoryId : 2
             * goodsId : 2
             * goodsName : 1
             * goodsUnit : 2
             * goodsCount : 20
             * goodsImage : 6
             */

            private String repertoryId;
            private String goodsId;
            private String goodsName;
            private String goodsUnit;
            private String goodsCount;
            private String goodsImage;

            public String getRepertoryId() {
                return repertoryId;
            }

            public void setRepertoryId(String repertoryId) {
                this.repertoryId = repertoryId;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsUnit() {
                return goodsUnit;
            }

            public void setGoodsUnit(String goodsUnit) {
                this.goodsUnit = goodsUnit;
            }

            public String getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(String goodsCount) {
                this.goodsCount = goodsCount;
            }

            public String getGoodsImage() {
                return goodsImage;
            }

            public void setGoodsImage(String goodsImage) {
                this.goodsImage = goodsImage;
            }
        }
    }
}
