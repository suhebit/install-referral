package com.suheb.referralapp;

/**
 * Created by suheb on 27/11/16.
 */
public class Application extends android.app.Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Utility.setFirstLaunch(this);
    }


}
