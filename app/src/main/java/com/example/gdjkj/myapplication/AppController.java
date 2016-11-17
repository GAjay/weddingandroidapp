package com.example.gdjkj.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.example.gdjkj.myapplication.service.BackgroundSoundService;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

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
//File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())

                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSizePercentage(25)
                // default
                //.discCache(new UnlimitedDiscCache(cacheDir)) // default
                //.discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(getApplicationContext())) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);
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
