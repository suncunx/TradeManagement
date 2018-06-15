package com.trade.deliver;

/**
 * Created by Stephen Sun on 2018/5/5 0005.
 * Email:suncunx@qq.com
 */

public class OutBillResultBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : {"outBill":{"outBillId":"1006","goodsId":"7","goodsName":"牛奶","goodsUnit":"包","goodsOutUnitPrice":"4.0","goodsImage":"http://192.168.23.1:8080/TradeManagement/image/20180429120914.jpg","goodsCount":"2","totalPrice":"8.0","customerId":"2","customerName":"zhang","customerPhone":"10010","customerAddress":"suzhou","payStatus":"1","deliverId":"1002","deliverMan":"李勇","deliverPhone":"15850683705","deliverManStatus":"1","deliverStatus":"交易完成","time":"2018-04-01 21:08:20.0"}}
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
        /**
         * outBill : {"outBillId":"1006","goodsId":"7","goodsName":"牛奶","goodsUnit":"包","goodsOutUnitPrice":"4.0","goodsImage":"http://192.168.23.1:8080/TradeManagement/image/20180429120914.jpg","goodsCount":"2","totalPrice":"8.0","customerId":"2","customerName":"zhang","customerPhone":"10010","customerAddress":"suzhou","payStatus":"1","deliverId":"1002","deliverMan":"李勇","deliverPhone":"15850683705","deliverManStatus":"1","deliverStatus":"交易完成","time":"2018-04-01 21:08:20.0"}
         */

        private OutBillBean outBill;

        public OutBillBean getOutBill() {
            return outBill;
        }

        public void setOutBill(OutBillBean outBill) {
            this.outBill = outBill;
        }

        public static class OutBillBean {
            /**
             * outBillId : 1006
             * goodsId : 7
             * goodsName : 牛奶
             * goodsUnit : 包
             * goodsOutUnitPrice : 4.0
             * goodsImage : http://192.168.23.1:8080/TradeManagement/image/20180429120914.jpg
             * goodsCount : 2
             * totalPrice : 8.0
             * customerId : 2
             * customerName : zhang
             * customerPhone : 10010
             * customerAddress : suzhou
             * payStatus : 1
             * deliverId : 1002
             * deliverMan : 李勇
             * deliverPhone : 15850683705
             * deliverManStatus : 1
             * deliverStatus : 交易完成
             * time : 2018-04-01 21:08:20.0
             */

            private String outBillId;
            private String goodsId;
            private String goodsName;
            private String goodsUnit;
            private String goodsOutUnitPrice;
            private String goodsImage;
            private String goodsCount;
            private String totalPrice;
            private String customerId;
            private String customerName;
            private String customerPhone;
            private String customerAddress;
            private String payStatus;
            private String deliverId;
            private String deliverMan;
            private String deliverPhone;
            private String deliverManStatus;
            private String deliverStatus;
            private String time;

            public String getOutBillId() {
                return outBillId;
            }

            public void setOutBillId(String outBillId) {
                this.outBillId = outBillId;
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

            public String getGoodsOutUnitPrice() {
                return goodsOutUnitPrice;
            }

            public void setGoodsOutUnitPrice(String goodsOutUnitPrice) {
                this.goodsOutUnitPrice = goodsOutUnitPrice;
            }

            public String getGoodsImage() {
                return goodsImage;
            }

            public void setGoodsImage(String goodsImage) {
                this.goodsImage = goodsImage;
            }

            public String getGoodsCount() {
                return goodsCount;
            }

            public void setGoodsCount(String goodsCount) {
                this.goodsCount = goodsCount;
            }

            public String getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(String totalPrice) {
                this.totalPrice = totalPrice;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getCustomerPhone() {
                return customerPhone;
            }

            public void setCustomerPhone(String customerPhone) {
                this.customerPhone = customerPhone;
            }

            public String getCustomerAddress() {
                return customerAddress;
            }

            public void setCustomerAddress(String customerAddress) {
                this.customerAddress = customerAddress;
            }

            public String getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(String payStatus) {
                this.payStatus = payStatus;
            }

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

            public String getDeliverStatus() {
                return deliverStatus;
            }

            public void setDeliverStatus(String deliverStatus) {
                this.deliverStatus = deliverStatus;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
