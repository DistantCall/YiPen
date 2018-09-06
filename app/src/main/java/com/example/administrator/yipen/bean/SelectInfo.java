package com.example.administrator.yipen.bean;

import java.util.List;

public class SelectInfo {

    /**
     * status : 1
     * message : 用户信息查询成功
     * result : [{"truename":null,"start_time":"2018-09-09","stop_time":"2018-09-16","area_name":"长丰元1区","floor":1,"unit":"2","room":"2309","area":"98","assessment":"1.00"},{"truename":null,"start_time":"2018-09-09","stop_time":"2018-09-16","area_name":"波普中心","floor":312,"unit":"321","room":"11","area":"123","assessment":null}]
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
         * truename : null
         * start_time : 2018-09-09
         * stop_time : 2018-09-16
         * area_name : 长丰元1区
         * floor : 1
         * unit : 2
         * room : 2309
         * area : 98
         * assessment : 1.00
         */

        private Object truename;
        private String start_time;
        private String stop_time;
        private String area_name;
        private int floor;
        private String unit;
        private String room;
        private String area;
        private String assessment;

        public Object getTruename() {
            return truename;
        }

        public void setTruename(Object truename) {
            this.truename = truename;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getStop_time() {
            return stop_time;
        }

        public void setStop_time(String stop_time) {
            this.stop_time = stop_time;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAssessment() {
            return assessment;
        }

        public void setAssessment(String assessment) {
            this.assessment = assessment;
        }
    }
}
