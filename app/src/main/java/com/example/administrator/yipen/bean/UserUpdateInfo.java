package com.example.administrator.yipen.bean;

import java.util.List;

public class UserUpdateInfo {

    /**
     * status : 1
     * message : 数据修改成功
     * result : [{"username":null,"nickname":null,"truename":null,"sex":"男","type":"普通会员","email":"","age":null,"team_status":"未加入组织","address":"00","create_time":"2018-09-01 19:35:51","last_login_time":"2018-09-01 19:35:51","is_fenxiao":"不是分销会员","code_url":"https://yp.dxshuju.com/static/img/5.jpg","token":"b19cf8204fef84fc7e093efa03fb2c22"}]
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
         * username : null
         * nickname : null
         * truename : null
         * sex : 男
         * type : 普通会员
         * email :
         * age : null
         * team_status : 未加入组织
         * address : 00
         * create_time : 2018-09-01 19:35:51
         * last_login_time : 2018-09-01 19:35:51
         * is_fenxiao : 不是分销会员
         * code_url : https://yp.dxshuju.com/static/img/5.jpg
         * token : b19cf8204fef84fc7e093efa03fb2c22
         */

        private Object username;
        private Object nickname;
        private Object truename;
        private String sex;
        private String type;
        private String email;
        private Object age;
        private String team_status;
        private String address;
        private String create_time;
        private String last_login_time;
        private String is_fenxiao;
        private String code_url;
        private String token;

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getTruename() {
            return truename;
        }

        public void setTruename(Object truename) {
            this.truename = truename;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public String getTeam_status() {
            return team_status;
        }

        public void setTeam_status(String team_status) {
            this.team_status = team_status;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getIs_fenxiao() {
            return is_fenxiao;
        }

        public void setIs_fenxiao(String is_fenxiao) {
            this.is_fenxiao = is_fenxiao;
        }

        public String getCode_url() {
            return code_url;
        }

        public void setCode_url(String code_url) {
            this.code_url = code_url;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
