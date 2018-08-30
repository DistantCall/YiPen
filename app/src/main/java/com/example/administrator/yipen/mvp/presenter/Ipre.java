package com.example.administrator.yipen.mvp.presenter;

public interface Ipre  {
    void Scuess(Object o, int requestCode);

    void Error(Throwable e);

    void LoginScuess(Object o);

    void LoginErr(Throwable t);
}
