package com.example.gdjkj.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.adapter.WordAdapter;
import com.example.gdjkj.myapplication.utlis.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gdjkj on 10/24/16.
 */

public class FunctionsFragment extends Fragment {

    public FunctionsFragment() {
    }
    private List<Word> functionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private WordAdapter wAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_functions, container, false);
        final ImageView rl = (ImageView) rootView.findViewById(R.id.imageView_header);
        Glide.with(getContext())
                .load("https://lh3.googleusercontent.com/Easlpbh7MVDGAy8Y3GXRt0YGpEi19Q_12UnXpIwEADcVW7YaIa60EPDQyG7bsB0PEf6uBhhJIfYuzeCkipWoV5IL1hGiVieCGlBKoPuUIRsNYlBahdy6SHFUlTn3Sc8kNtSu")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.colorPrimary)
                .crossFade()
                .into(rl);
        //System.gc();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        wAdapter = new WordAdapter(functionList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(wAdapter);

        prepareMovieData();
        System.gc();
        return rootView;
    }

    private void prepareMovieData() {
//Word(String functionName, String dateOfFunction, String timeOfFunction , String location)
        Word movie = new Word("HaathKaam", "17 January, 2017", "1:30PM","Industrial Area, Rani Bazar, Bikaner");
        functionList.add(movie);// Baraat parsthan,,

        movie = new Word("Sangeet Sandhya", "18 January, 2017", "6:00PM","Ghodi Parashvnath, " +
                "Goga Gate, Bikaner");
        functionList.add(movie);

        movie = new Word("Mayara", "19 January, 2017", "6:15PM","Ghodi Parashvnath, " +
                "Goga Gate, Bikaner");
        functionList.add(movie);

        movie = new Word("Baraat Parsthan", "19 January, 2017", "6:15PM","Ghodi Parashvnath, " +
                "Goga Gate, Bikaner");
        functionList.add(movie);

        movie = new Word("SvruchiBhoj", "19 January, 2017", "7:00PM","Ghodi Parashvnath, Goga Gate, Bikaner");
        functionList.add(movie);

        wAdapter.notifyDataSetChanged();
    }


}
