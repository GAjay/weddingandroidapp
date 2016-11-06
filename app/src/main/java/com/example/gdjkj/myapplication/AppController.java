package com.example.gdjkj.myapplication;

import android.app.Application;

/**
 * Created by gdjkj on 11/6/16.
 */

public class AppController extends Application {

    private static AppController controller;

    public static AppController getController() {
        return controller;
    }
    //public static ImageLoaderConfiguration config;
    public static int deviceWidth;



}
