package com.example.administrator.yipen.utils;

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
     * res : [{"username":"用户名","nickname":"昵称","truename":"真实姓名","type":"类型","email":"邮箱","id_number":"身份证号","sex":"性别","age":"年龄","qq":"QQ号","jifen":"积分"}]
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
                    ", nickname='" + nickname + '\'' +
                    ", truename='" + truename + '\'' +
                    ", type='" + type + '\'' +
                    ", email='" + email + '\'' +
                    ", id_number='" + id_number + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age='" + age + '\'' +
                    ", qq='" + qq + '\'' +
                    ", jifen='" + jifen + '\'' +
                    '}';
        }

        /**
         * username : 用户名
         * nickname : 昵称
         * truename : 真实姓名
         * type : 类型
         * email : 邮箱
         * id_number : 身份证号
         * sex : 性别
         * age : 年龄
         * qq : QQ号
         * jifen : 积分
         */

        private String username;
        private String nickname;
        private String truename;
        private String type;
        private String email;
        private String id_number;
        private String sex;
        private String age;
        private String qq;
        private String jifen;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
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

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }
    }
}
