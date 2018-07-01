package com.trade.goods.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Stephen Sun on 2018/4/18 0018.
 * Email:suncunx@qq.com
 */

public class GoodsResultBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : {"goodss":[{"goodsId":"1","name":"巧克力","unit":"箱","inUnitPrice":"100.0","outUnitPrice":"110.0","image":"2","supplierId":"4","supplierName":"中国移动","supplierPhone":"10086","supplierAddress":"新街口"},{"goodsId":"2","name":"1","unit":"2","inUnitPrice":"1.2","outUnitPrice":"4.5","image":"6","supplierId":"1","supplierName":"阿里巴巴","supplierPhone":"13675234396","supplierAddress":"南京工程学院"}]}
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
        private List<GoodsBean> goodss;

        public List<GoodsBean> getGoodss() {
            return goodss;
        }

        public void setGoodss(List<GoodsBean> goodss) {
            this.goodss = goodss;
        }

        public static class GoodsBean implements Serializable{
            /**
             * goodsId : 1
             * name : 巧克力
             * unit : 箱
             * inUnitPrice : 100.0
             * outUnitPrice : 110.0
             * image : 2
             * repertory : 2
             * supplierId : 4
             * supplierName : 中国移动
             * supplierPhone : 10086
             * supplierAddress : 新街口
             */

            private String goodsId;
            private String name;
            private String unit;
            private String inUnitPrice;
            private String outUnitPrice;
            private String image;
            private String repertory;
            private String supplierId;
            private String supplierName;
            private String supplierPhone;
            private String supplierAddress;

            public String getRepertory() {
                return repertory;
            }

            public void setRepertory(String repertory) {
                this.repertory = repertory;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getInUnitPrice() {
                return inUnitPrice;
            }

            public void setInUnitPrice(String inUnitPrice) {
                this.inUnitPrice = inUnitPrice;
            }

            public String getOutUnitPrice() {
                return outUnitPrice;
            }

            public void setOutUnitPrice(String outUnitPrice) {
                this.outUnitPrice = outUnitPrice;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
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
        }
    }
}
