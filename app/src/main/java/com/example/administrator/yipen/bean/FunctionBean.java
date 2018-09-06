package com.example.administrator.yipen.bean;

public class FunctionBean {
private String logoText;
private int logoIMG;

    public String getLogoText() {
        return logoText;
    }

    public void setLogoText(String logoText) {
        this.logoText = logoText;
    }

    public int getLogoIMG() {
        return logoIMG;
    }

    public void setLogoIMG(int logoIMG) {
        this.logoIMG = logoIMG;
    }

    public FunctionBean(String logoText, int logoIMG) {
        this.logoText = logoText;
        this.logoIMG = logoIMG;
    }
}
