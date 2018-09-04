package com.example.administrator.yipen.bean;

import java.util.List;

public class LoginBean {


    /**
     * status : 1
     * message : 登录成功
     * result : [{"bis_id":0,"mem_id":0,"username":null,"code_url":"https://yp.dxshuju.com/static/img/5.jpg","telephone":"18810415234","create_time":"2018-08-29 10:18:02","token":"2c476031335097250d3b4664ec35f473"}]
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

    @Override
    public String toString() {
        return "LoginBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    public static class ResultBean {
        @Override
        public String toString() {
            return "ResultBean{" +
                    "bis_id=" + bis_id +
                    ", mem_id=" + mem_id +
                    ", username=" + username +
                    ", code_url='" + code_url + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }

        /**
         * bis_id : 0
         * mem_id : 0
         * username : null
         * code_url : https://yp.dxshuju.com/static/img/5.jpg
         * telephone : 18810415234
         * create_time : 2018-08-29 10:18:02
         * token : 2c476031335097250d3b4664ec35f473
         */

        private int bis_id;
        private int mem_id;
        private Object username;
        private String code_url;
        private String telephone;
        private String create_time;
        private String token;

        public int getBis_id() {
            return bis_id;
        }

        public void setBis_id(int bis_id) {
            this.bis_id = bis_id;
        }

        public int getMem_id() {
            return mem_id;
        }

        public void setMem_id(int mem_id) {
            this.mem_id = mem_id;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public String getCode_url() {
            return code_url;
        }

        public void setCode_url(String code_url) {
            this.code_url = code_url;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
