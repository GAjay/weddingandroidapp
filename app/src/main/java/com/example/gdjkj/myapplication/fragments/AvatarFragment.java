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
public class AvatarFragment extends Fragment {


    public AvatarFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quotes, container, false);
        return rootView;
    }
}