package com.example.gdjkj.myapplication.activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.gdjkj.myapplication.R;

/**
 * Created by gdjkj on 10/23/16.
 */


public class SplashScreenActivity extends AppCompatActivity {
    /**AN screen for splash screen
     *@param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreenActivity.this, DashboardActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 3000);

    }
}