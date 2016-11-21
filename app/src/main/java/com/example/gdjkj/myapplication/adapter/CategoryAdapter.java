package com.example.gdjkj.myapplication.adapter;

/**
 * Created by gdjkj on 10/24/16.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.gdjkj.myapplication.fragments.ContactFragment;
import com.example.gdjkj.myapplication.fragments.FunctionsFragment;
import com.example.gdjkj.myapplication.fragments.GalleryFragment;
import com.example.gdjkj.myapplication.fragments.infoFragment;

/**
 * {@link CategoryAdapter} is a {@link FragmentPagerAdapter} that can provide the layout for
 * each list item based on a data source which is a list of {@link } objects.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FunctionsFragment();
        } /*else if (position == 1) {
            return new GalleryFragment();
        } */else if(position == 1){
            return new ContactFragment();
        }else {
            return new infoFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        /*if (position == 0) {
            return mContext.getString(R.string.funtions);
        } else if (position == 1) {
            return mContext.getString(R.string.gallery);
        } else {
            return mContext.getString(R.string.aboutdeveloper);
        }*/
        return "";
    }
}

