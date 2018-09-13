package com.example.administrator.yipen.bean;

public class FileBean {

    @Override
    public String toString() {
        return "FileBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    /**
     * status : 1
     * message : 图片上传成功
     * result : https://yp.dxshuju.com/img/20180901/0f395ee27e066d9c01255af18231e1af.jpg
     */

    private int status;
    private String message;
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
