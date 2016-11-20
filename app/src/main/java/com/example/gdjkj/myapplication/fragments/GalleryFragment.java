package com.example.gdjkj.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.adapter.ImageAdapter;

/**
 * Created by gdjkj on 10/24/16.
 */

public class GalleryFragment extends Fragment {

    int[] imageId = {
            R.drawable.ic_grid_1,
            R.drawable.ic_grid_2,
            R.drawable.ic_grid_3,
            R.drawable.ic_grid_4,
            R.drawable.ic_grid_5,
    };
    public GalleryFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        GridView grid ;
        ImageAdapter adapter = new ImageAdapter(getActivity(), imageId);
        grid=(GridView)rootView.findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                /*Toast.makeText(getActivity(), "You Clicked at " , Toast.LENGTH_SHORT).show();*/

            }
        });

        return rootView;

    }
}
