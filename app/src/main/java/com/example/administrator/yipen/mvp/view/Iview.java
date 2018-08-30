package com.example.administrator.yipen.mvp.view;

public interface Iview {
    void Scuess(Object o, int requestCode);

    void Error(Throwable e);

    void LoginScuess(Object o);

    void LoginErr(Throwable t);
}
