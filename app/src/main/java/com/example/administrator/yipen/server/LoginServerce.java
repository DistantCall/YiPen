package com.example.administrator.yipen.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LoginServerce extends Service {
    public static boolean reflag=false;
  public static LoginInter loginInter;

    public LoginServerce( ) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

  public static void init(LoginInter inter){
        loginInter=inter;
      if(loginInter!=null) {
          if (reflag) {
              loginInter.login();
          } else {
              loginInter.notLogin();
          }
      }
  }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
             init(loginInter);
        return super.onStartCommand(intent, flags, startId);
    }
}
