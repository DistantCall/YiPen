package com.example.administrator.yipen.bean;

import java.util.List;

public class RegBean {

    @Override
    public String toString() {
        return "RegBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    /**
     * status : 1
     * message : 第一次注册成功
     * result : [{"status":"状态  0：普通用户  1：已注册用户","create_time":"创建时间"}]
     */

    private int status;
    private String message;
    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        @Override
        public String toString() {
            return "ResultBean{" +
                    "status='" + status + '\'' +
                    ", create_time='" + create_time + '\'' +
                    '}';
        }

        /**
         * status : 状态  0：普通用户  1：已注册用户
         * create_time : 创建时间
         */

        private String status;
        private String create_time;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
