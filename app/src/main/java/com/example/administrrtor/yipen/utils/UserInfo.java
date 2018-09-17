package com.example.administrrtor.yipen.utils;

import java.util.List;

public class UserInfo {

    @Override
    public String toString() {
        return "UserInfo{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", res=" + res +
                '}';
    }

    /**
     * status : 1
     * message : 用户信息查询成功
     * res : [{"username":"admin","nickname":null,"truename":"安安","type":0,"email":"","id_number":"","sex":1,"age":22,"qq":null,"jifen":"0.00","code_url":"20180904/bff83d8584f1d28e03abad88f9ea8055.jpg"}]
     */

    private int status;
    private String message;
    private List<ResBean> res;

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

    public List<ResBean> getRes() {
        return res;
    }

    public void setRes(List<ResBean> res) {
        this.res = res;
    }

    public static class ResBean {
        @Override
        public String toString() {
            return "ResBean{" +
                    "username='" + username + '\'' +
                    ", nickname=" + nickname +
                    ", truename='" + truename + '\'' +
                    ", type=" + type +
                    ", email='" + email + '\'' +
                    ", id_number='" + id_number + '\'' +
                    ", sex=" + sex +
                    ", age=" + age +
                    ", qq=" + qq +
                    ", jifen='" + jifen + '\'' +
                    ", code_url='" + code_url + '\'' +
                    '}';
        }

        /**
         * username : admin
         * nickname : null
         * truename : 安安
         * type : 0
         * email :
         * id_number :
         * sex : 1
         * age : 22
         * qq : null
         * jifen : 0.00
         * code_url : 20180904/bff83d8584f1d28e03abad88f9ea8055.jpg
         */

        private String username;
        private Object nickname;
        private String truename;
        private int type;
        private String email;
        private String id_number;
        private int sex;
        private int age;
        private Object qq;
        private String jifen;
        private String code_url;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Object getQq() {
            return qq;
        }

        public void setQq(Object qq) {
            this.qq = qq;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getCode_url() {
            return code_url;
        }

        public void setCode_url(String code_url) {
            this.code_url = code_url;
        }
    }
}
