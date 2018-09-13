package com.example.administrator.yipen.bean;

import java.util.List;

public class RegBean {


    /**
     * status : 1
     * message : 第一次注册成功
     * result : [{"status":0,"create_time":"2018-08-29 10:18:02"}]
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
        /**
         * status : 0
         * create_time : 2018-08-29 10:18:02
         */

        private int status;
        private String create_time;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
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
