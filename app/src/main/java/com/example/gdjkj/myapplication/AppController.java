package com.example.gdjkj.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.example.gdjkj.myapplication.service.BackgroundSoundService;

/**
 * Created by gdjkj on 11/6/16.
 */

public class AppController extends Application implements
        Application.ActivityLifecycleCallbacks  {

    private static AppController controller;

    public static AppController getController() {
        return controller;
    }
    //public static ImageLoaderConfiguration config;
    public static int deviceWidth;

    @Override
    public void onCreate() {
        super.onCreate();
        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Intent svc=new Intent(this, BackgroundSoundService.class);
        stopService(svc);
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        Intent svc=new Intent(activity, BackgroundSoundService.class);
        startService(svc);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Intent svc=new Intent(activity, BackgroundSoundService.class);
        stopService(svc);
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
