package com.example.administrator.yipen.bean;

import java.util.List;

public class SelectInfo {


    /**
     * status : 1
     * message : 用户信息查询成功
     * result : [{"truename":"用户名","start_time":"开始时间","stop_time":"结束时间","area_name":"哪个小区","floor":"几单元","unit":"几号楼","room":"房间号","area":"多少平米","assessment":"所需缴费金额"}]
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
         * truename : 用户名
         * start_time : 开始时间
         * stop_time : 结束时间
         * area_name : 哪个小区
         * floor : 几单元
         * unit : 几号楼
         * room : 房间号
         * area : 多少平米
         * assessment : 所需缴费金额
         */

        private String truename;
        private String start_time;
        private String stop_time;
        private String area_name;
        private String floor;
        private String unit;
        private String room;
        private String area;
        private String assessment;

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
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

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
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
