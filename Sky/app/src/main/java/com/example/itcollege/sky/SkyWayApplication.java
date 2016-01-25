package com.example.itcollege.sky;

import android.app.Application;

public class SkyWayApplication extends Application{
    public static SkyWayRoundUp _skyWay;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static SkyWayRoundUp getSkyWay() {
        return _skyWay;
    }

    public static void setSkyWay(SkyWayRoundUp skyWay) {
        SkyWayApplication._skyWay = skyWay;
    }

}
