package com.trade.home.model;

import java.util.List;

/**
 * Created by Stephen Sun on 2017/8/16 0016.
 * Email:suncunx@qq.com
 */

public class HomeResultBean {

    /**
     * code : 200
     * msg : 查询成功
     * result : [{"id":1,"name":"头条","description":"top"},{"id":2,"name":"澳洲","description":"aozhou"}]
     */

    private String code;
    private String msg;
    private List<ResultBean> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * name : 头条
         * description : top
         */

        private int id;
        private String name;
        private String description;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
