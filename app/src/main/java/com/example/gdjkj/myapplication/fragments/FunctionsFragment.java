package com.example.gdjkj.myapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*import com.example.gdjkj.myapplication.adapter.ListviewContactAdapter;*/
import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.adapter.WordAdapter;
import com.example.gdjkj.myapplication.service.BackgroundSoundService;
import com.example.gdjkj.myapplication.utlis.Word;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by gdjkj on 10/24/16.
 */

public class FunctionsFragment extends Fragment {
    TextView text, vers;

    public FunctionsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_functions, container, false);

        final LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.event_layout);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .build();
        int deviceWidth = getResources().getDisplayMetrics().widthPixels;
        int deviceHeight = 5 * deviceWidth;
        ImageSize targetSize = new ImageSize(deviceWidth, deviceHeight);
        imageLoader.loadImage("drawable://" + R.drawable.ic_bg, targetSize, options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Do whatever you want with Bitmap
                BitmapDrawable background = new BitmapDrawable(loadedImage);
                ll.setBackgroundDrawable(background);
            }
        });
        RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.header_function);
        Bitmap myImage = BitmapFactory.decodeResource(getResources(), R.drawable.marriage_bg);
        Drawable dr = new BitmapDrawable(myImage);
        rl.setBackgroundDrawable(dr);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Ganesh Sthapna", "1 December, 2016", "12:30 Pm", "Sevgo Ki Baghichi, Bhaskar Mohalla, Pokaran"));
        words.add(new Word("Ghdi Vinayak", "1 December, 2016", "07:15 PM", "Sevgo Ki Baghichi, Bhaskar Mohalla, Pokaran"));
        words.add(new Word("Yagyopavit Sanskar", "2 December, 2016", "10:15 AM", "Sevgo Ki Baghichi, Bhaskar Mohalla, Pokaran"));
        words.add(new Word("Panigrahan Sanskar", "3 December, 2016", "After Mid Night", "Maheshwari Nyati Nohra,  Nadi Mohalla, Pali"));
        words.add(new Word("Pritibhoj", "4 December, 2016", "07:15 PM", "Sevgo Ki Baghichi, Bhaskar Mohalla, Pokaran"));

        WordAdapter adapter = new WordAdapter(getActivity(), words);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        return rootView;
    }


}
