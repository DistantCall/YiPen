package com.example.administrator.yipen.bean;

public class FileBean {

    /**
     * status : 1
     * message : 图片上传成功
     * result : 20180827\3445029d365ced723dfebd498a38cca3.jpg
     */

    private int status;
    private String message;

    @Override
    public String toString() {
        return "FileBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

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
