package com.example.gdjkj.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gdjkj.myapplication.R;

/**
 * Created by gdjkj on 11/6/16.
 */
public class infoFragment extends Fragment {

    //http://www.hindustantimes.com/rf/image_size_800x600/HT/p2/2016/05/26/Pictures/_65d9bb82-2327-11e6-bd64-8acd98c1ae00.jpg
    public infoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        return rootView;
    }
}