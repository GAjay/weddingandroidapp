package com.example.gdjkj.myapplication.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.gdjkj.myapplication.AppController;

import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.adapter.CategoryAdapter;
import com.example.gdjkj.myapplication.fragments.ContactFragment;
import com.example.gdjkj.myapplication.fragments.RespondFragment;
import com.example.gdjkj.myapplication.fragments.SendmessageFragment;
import com.example.gdjkj.myapplication.service.BackgroundSoundService;

import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {
    TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.ic_events,
            R.drawable.ic_contact,
            R.drawable.ic_avatar

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

       /* // create bitmap from resource
        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.icon2);*/

        // set circle bitmap
      /*  ImageView mImage = (ImageView) findViewById(R.id.image);
        mImage.setImageBitmap(getCircleBitmap(bm));*/
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Find the tab layout that shows the tabs
         tabLayout = (TabLayout) findViewById(R.id.tabs);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }




    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);

       // tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }



    @Override
    protected void onResume() {
        AppController controller=(AppController)getApplicationContext();
        controller.onActivityResumed(DashboardActivity.this);
        super.onResume();

    }

    @Override
    protected void onPause() {
        AppController controller=(AppController)getApplicationContext();
        controller.onActivityPaused(DashboardActivity.this);
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("resultcode"+resultCode);
        System.out.println("requestcode"+requestCode);
        if(requestCode == 102) {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.viewpager);
            if (f instanceof ContactFragment) {
                f.onActivityResult(requestCode, resultCode, data);
            }
        }
        else if(requestCode == 103){
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.viewpager);
            if (f instanceof RespondFragment) {
                f.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
