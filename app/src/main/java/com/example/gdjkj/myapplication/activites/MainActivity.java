package com.example.gdjkj.myapplication.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.gdjkj.myapplication.AppController;
import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.service.BackgroundSoundService;


/**
 * Created by gdjkj on 11/6/16.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        TextView textView = (TextView)findViewById(R.id.button);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(i);


            }
        });
    }
    @Override
    protected void onResume() {
        AppController controller=(AppController)getApplicationContext();
        controller.onActivityResumed(MainActivity.this);
        super.onResume();


    }

    @Override
    public void onPause() {
        AppController controller=(AppController)getApplicationContext();
        controller.onActivityPaused(MainActivity.this);
        super.onPause();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();



    }

}
