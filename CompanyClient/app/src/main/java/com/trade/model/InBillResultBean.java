package com.trade.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Stephen Sun on 2018/4/18 0018.
 * Email:suncunx@qq.com
 */

public class InBillResultBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : {"inBills":[{"inBillId":"1","goodsId":"1","goodsName":"巧克力","goodsUnit":"箱","goodsInUnitPrice":"2","goodsImage":"100.0","goodsCount":"20","totalPrice":"2000.0","supplierId":"4","supplierName":"中国移动","supplierPhone":"10086","supplierAddress":"新街口","time":"2018-04-17 16:33:10.0"},{"inBillId":"2","goodsId":"1","goodsName":"巧克力","goodsUnit":"箱","goodsInUnitPrice":"2","goodsImage":"100.0","goodsCount":"20","totalPrice":"2000.0","supplierId":"1","supplierName":"阿里巴巴","supplierPhone":"13675234396","supplierAddress":"南京工程学院","time":"2018-04-17 16:34:35.0"},{"inBillId":"3","goodsId":"2","goodsName":"1","goodsUnit":"2","goodsInUnitPrice":"6","goodsImage":"1.2","goodsCount":"30","totalPrice":"36.0","supplierId":"1","supplierName":"阿里巴巴","supplierPhone":"13675234396","supplierAddress":"南京工程学院","time":"2018-04-17 16:37:41.0"}]}
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
        private String pageNo;
        private String pageSize;
        private String pageTotal;
        private List<InBillBean> inBills;

        public List<InBillBean> getInBills() {
            return inBills;
        }

        public void setInBills(List<InBillBean> inBills) {
            this.inBills = inBills;
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

        public static class InBillBean implements Serializable{
            /**
             * inBillId : 1
             * goodsId : 1
             * goodsName : 巧克力
             * goodsUnit : 箱
             * goodsInUnitPrice : 2
             * goodsImage : 100.0
             * goodsCount : 20
             * totalPrice : 2000.0
             * supplierId : 4
             * supplierName : 中国移动
             * supplierPhone : 10086
             * supplierAddress : 新街口
             * time : 2018-04-17 16:33:10.0
             */

            private String inBillId;
            private String goodsId;
            private String goodsName;
            private String goodsUnit;
            private String goodsInUnitPrice;
            private String goodsImage;
            private String goodsCount;
            private String totalPrice;
            private String supplierId;
            private String supplierName;
            private String supplierPhone;
            private String supplierAddress;
            private String time;

            public String getInBillId() {
                return inBillId;
            }

            public void setInBillId(String inBillId) {
                this.inBillId = inBillId;
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

            public String getGoodsInUnitPrice() {
                return goodsInUnitPrice;
            }

            public void setGoodsInUnitPrice(String goodsInUnitPrice) {
                this.goodsInUnitPrice = goodsInUnitPrice;
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

            public String getSupplierId() {
                return supplierId;
            }

            public void setSupplierId(String supplierId) {
                this.supplierId = supplierId;
            }

            public String getSupplierName() {
                return supplierName;
            }

            public void setSupplierName(String supplierName) {
                this.supplierName = supplierName;
            }

            public String getSupplierPhone() {
                return supplierPhone;
            }

            public void setSupplierPhone(String supplierPhone) {
                this.supplierPhone = supplierPhone;
            }

            public String getSupplierAddress() {
                return supplierAddress;
            }

            public void setSupplierAddress(String supplierAddress) {
                this.supplierAddress = supplierAddress;
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
