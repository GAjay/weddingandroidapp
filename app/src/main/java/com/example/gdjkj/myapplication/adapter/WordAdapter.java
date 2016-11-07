package com.example.gdjkj.myapplication.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.utlis.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gdjkj on 11/6/16.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.title);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        miwokTextView.setText(currentWord.getFunctionName());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.date_of_function);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        defaultTextView.setText(currentWord.getDateOfFunction());

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
       timeTextView.setText(currentWord.getPlaceOfFunction());
        TextView venueTextView = (TextView) listItemView.findViewById(R.id.venue);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        venueTextView.setText(currentWord.getLocationImage());





        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
