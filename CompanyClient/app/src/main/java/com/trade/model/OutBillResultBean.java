package com.trade.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Stephen Sun on 2018/4/18 0018.
 * Email:suncunx@qq.com
 */

public class OutBillResultBean {
    /**
     * code : 200
     * msg : 查询成功
     * result : {"outBills":[{"obId":"1","goodsId":"2","goodsName":"1","goodsUnit":"2","goodsOutUnitPrice":"4.5","goodsImage":"6","goodsCount":"6","totalPrice":"27.0","customerId":"1","customerName":"123","customerPhone":"456","customerAddress":"789","payStatus":"0","deliverId":"1","deliverMan":"马化腾","deliverPhone":"10086","deliverManStatus":"0","deliverStatus":"0","time":"2018-04-17 17:06:18.0"},{"obId":"2","goodsId":"1","goodsName":"巧克力","goodsUnit":"箱","goodsOutUnitPrice":"110.0","goodsImage":"2","goodsCount":"5","totalPrice":"550.0","customerId":"1","customerName":"123","customerPhone":"456","customerAddress":"789","payStatus":"0","deliverId":"1","deliverMan":"马化腾","deliverPhone":"10086","deliverManStatus":"0","deliverStatus":"0","time":"2018-04-17 17:07:35.0"}]}
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

    @Override
    public String toString() {
        return "OutBillResultBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }

    public static class ResultBean {
        private String pageNo;
        private String pageSize;
        private String pageTotal;
        private List<OutBillBean> outBills;

        public List<OutBillBean> getOutBills() {
            return outBills;
        }

        public void setOutBills(List<OutBillBean> outBills) {
            this.outBills = outBills;
        }

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPageTotal() {
            return pageTotal;
        }

        public void setPageTotal(String pageTotal) {
            this.pageTotal = pageTotal;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "outBills=" + outBills +
                    '}';
        }

        public static class OutBillBean implements Serializable{
            /**
             * outBillId : 1
             * goodsId : 2
             * goodsName : 1
             * goodsUnit : 2
             * goodsOutUnitPrice : 4.5
             * goodsImage : 6
             * goodsCount : 6
             * totalPrice : 27.0
             * customerId : 1
             * customerName : 123
             * customerPhone : 456
             * customerAddress : 789
             * payStatus : 0
             * deliverId : 1
             * deliverMan : 马化腾
             * deliverPhone : 10086
             * deliverManStatus : 0
             * deliverStatus : 0
             * time : 2018-04-17 17:06:18.0
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

            @Override
            public String toString() {
                return "OutBillBean{" +
                        "outBillId='" + outBillId + '\'' +
                        ", goodsId='" + goodsId + '\'' +
                        ", goodsName='" + goodsName + '\'' +
                        ", goodsUnit='" + goodsUnit + '\'' +
                        ", goodsOutUnitPrice='" + goodsOutUnitPrice + '\'' +
                        ", goodsImage='" + goodsImage + '\'' +
                        ", goodsCount='" + goodsCount + '\'' +
                        ", totalPrice='" + totalPrice + '\'' +
                        ", customerId='" + customerId + '\'' +
                        ", customerName='" + customerName + '\'' +
                        ", customerPhone='" + customerPhone + '\'' +
                        ", customerAddress='" + customerAddress + '\'' +
                        ", payStatus='" + payStatus + '\'' +
                        ", deliverId='" + deliverId + '\'' +
                        ", deliverMan='" + deliverMan + '\'' +
                        ", deliverPhone='" + deliverPhone + '\'' +
                        ", deliverManStatus='" + deliverManStatus + '\'' +
                        ", deliverStatus='" + deliverStatus + '\'' +
                        ", time='" + time + '\'' +
                        '}';
            }
        }
    }
}
