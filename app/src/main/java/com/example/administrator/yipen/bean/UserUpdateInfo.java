package com.example.administrator.yipen.bean;

import java.util.List;

public class UserUpdateInfo {
    @Override
    public String toString() {
        return "UserUpdateInfo{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    /**
     * status : 1
     * message : 数据修改成功
     * result : [{"username":"用户名","nickname":"昵称","truename":"真实姓名","sex":"性别","type":"用户类型","email":"邮箱","age":"年龄","team_status":"是否加入组织","address":"地址","create_time":"创建时间","last_login_time":"最后登录时间","is_fenxiao":"是否为分销会员","code_url":"图片地址","token":"生成的token"}]
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
                    "username='" + username + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", truename='" + truename + '\'' +
                    ", sex='" + sex + '\'' +
                    ", type='" + type + '\'' +
                    ", email='" + email + '\'' +
                    ", age='" + age + '\'' +
                    ", team_status='" + team_status + '\'' +
                    ", address='" + address + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", last_login_time='" + last_login_time + '\'' +
                    ", is_fenxiao='" + is_fenxiao + '\'' +
                    ", code_url='" + code_url + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }

        /**
         * username : 用户名
         * nickname : 昵称
         * truename : 真实姓名
         * sex : 性别
         * type : 用户类型
         * email : 邮箱
         * age : 年龄
         * team_status : 是否加入组织
         * address : 地址
         * create_time : 创建时间
         * last_login_time : 最后登录时间
         * is_fenxiao : 是否为分销会员
         * code_url : 图片地址
         * token : 生成的token
         */

        private String username;
        private String nickname;
        private String truename;
        private String sex;
        private String type;
        private String email;
        private String age;
        private String team_status;
        private String address;
        private String create_time;
        private String last_login_time;
        private String is_fenxiao;
        private String code_url;
        private String token;

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

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
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
