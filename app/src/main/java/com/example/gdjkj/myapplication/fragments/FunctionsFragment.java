package com.example.gdjkj.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/*import com.example.gdjkj.myapplication.adapter.ListviewContactAdapter;*/
import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.adapter.WordAdapter;
import com.example.gdjkj.myapplication.service.BackgroundSoundService;
import com.example.gdjkj.myapplication.utlis.Word;

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


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("asda", "asdas", "asdasd", R.drawable.date_bg));
        words.add(new Word("asda", "asdas", "asdasd", R.drawable.date_bg));
        words.add(new Word("asda", "asdas", "asdasd", R.drawable.date_bg));
        words.add(new Word("asda", "asdas", "asdasd", R.drawable.date_bg));
        words.add(new Word("asda", "asdas", "asdasd", R.drawable.date_bg));
        words.add(new Word("asda", "asdas", "asdasd", R.drawable.date_bg));
        words.add(new Word("asda", "asdas", "asdasd", R.drawable.date_bg));
        words.add(new Word("asda", "asdas", "asdasd", R.drawable.date_bg));

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
