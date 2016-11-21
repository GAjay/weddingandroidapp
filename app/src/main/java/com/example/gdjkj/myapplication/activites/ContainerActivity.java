package com.example.gdjkj.myapplication.activites;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.gdjkj.myapplication.AppController;
import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.fragments.SendmessageFragment;


/**
 * Activity named container activity, use to hold only
 * different kind of projects.
 */
public class ContainerActivity extends AppCompatActivity {
    private Bundle resourceDataBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_container);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int screenName = getIntent().getIntExtra("screenName",0);
        loadScreen(screenName);
    }

    public void loadScreen(int screenName) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        android.support.v4.app.Fragment fragment = null;
        Bundle bundle = new Bundle();

        switch (screenName){
            case 102:
                fragment = new SendmessageFragment();
                break;
            case 103:
                fragment = new ReplymessageFragment();
                break;

        }
        fragmentTransaction.add(R.id.content_frame_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }else{
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                super.onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        AppController controller=(AppController)getApplicationContext();
        controller.onActivityResumed(ContainerActivity.this);
        super.onResume();

    }

    @Override
    protected void onPause() {
        AppController controller=(AppController)getApplicationContext();
        controller.onActivityPaused(ContainerActivity.this);
        super.onPause();

    }

}