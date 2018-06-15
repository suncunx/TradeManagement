package com.trade.model;

import java.util.List;

/**
 * Created by Stephen Sun on 2018/4/18 0018.
 * Email:suncunx@qq.com
 */

public class RepertoryResultBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : {"repertorys":[{"repertoryId":"2","goodsId":"2","goodsName":"1","goodsUnit":"2","goodsCount":"20","goodsImage":"6"},{"repertoryId":"3","goodsId":"1","goodsName":"巧克力","goodsUnit":"箱","goodsCount":"3","goodsImage":"2"}]}
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
        private List<RepertoryBean> repertories;

        public List<RepertoryBean> getRepertories() {
            return repertories;
        }

        public void setRepertorys(List<RepertoryBean> repertories) {
            this.repertories = repertories;
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
