package com.example.gdjkj.myapplication.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.gdjkj.myapplication.R;

import java.util.List;

/**
 * Created by gdjkj on 11/5/16.
 */

public class BackgroundSoundService extends Service {
    private static final String TAG = null;
    MediaPlayer player;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.idil);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);

    }
    public int onStartCommand(Intent intent, int flags, int startId) {
       // player.start();
        if(!isRunningInForeground()){
            onPause();
        }
        else{
            player.start();
        }
        return Service.START_NOT_STICKY;
    }


    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {
        player.stop();
        player.reset();
        player.release();
    }
    public void onPause() {
        player.stop();

        player.reset();
        player.release();
    }
    @Override
    public void onDestroy() {
        player.stop();
        player.reset();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
    protected boolean isRunningInForeground() {
        ActivityManager manager;
        manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = manager.getRunningTasks(1);
        if (tasks.isEmpty()) {
            return false;
        }
        String topActivityName = tasks.get(0).topActivity.getPackageName();
        return topActivityName.equalsIgnoreCase(getPackageName());
    }
}
