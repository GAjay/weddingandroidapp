package com.example.gdjkj.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.adapter.ImageAdapter;

/**
 * Created by gdjkj on 10/24/16.
 */

public class GalleryFragment extends Fragment {

    public GalleryFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(getActivity()));
        return rootView;

    }
}
