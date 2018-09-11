package com.example.administrator.yipen.bean;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginBean {
    @Override
    public String toString() {
        return "LoginBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    /**
     * status : 1
     * message : 登录成功
     * result : [{"bis_id":"商家id","mem_id\u201d":"用户id","username":"用户名","code_url":"图片路径","telephone":"联系电话","create_time":"创建时间","token":"生成的token"}]
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
                    "bis_id='" + bis_id + '\'' +
                    ", _$Mem_id224='" + mem_id + '\'' +
                    ", username='" + username + '\'' +
                    ", code_url='" + code_url + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }

        /**
         * bis_id : 商家id
         * mem_id” : 用户id
         * username : 用户名
         * code_url : 图片路径
         * telephone : 联系电话
         * create_time : 创建时间
         * token : 生成的token
         */

        private String bis_id;

        private String mem_id;
        private String username;
        private String code_url;
        private String telephone;
        private String create_time;
        private String token;

        public String getBis_id() {
            return bis_id;
        }

        public void setBis_id(String bis_id) {
            this.bis_id = bis_id;
        }

        public String getMem_id() {
            return mem_id;
        }

        public void setMem_id(String _$Mem_id224) {
            this.mem_id = _$Mem_id224;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
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
