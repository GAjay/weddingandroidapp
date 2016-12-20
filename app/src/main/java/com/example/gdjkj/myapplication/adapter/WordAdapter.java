package com.example.gdjkj.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.utlis.Word;

import java.util.List;

/**
 * Created by gdjkj on 11/6/16.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder> {

    private List<Word> funtionsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, timeOfFunctions, dateOfFunctions,Venue;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            dateOfFunctions = (TextView) view.findViewById(R.id.date_of_function);
            timeOfFunctions = (TextView) view.findViewById(R.id.time);
            Venue = (TextView) view.findViewById(R.id.venue);
        }
    }


    public WordAdapter(List<Word> funtionsList) {
        this.funtionsList = funtionsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Word movie = funtionsList.get(position);
        holder.title.setText(movie.getFunctionName());
        holder.dateOfFunctions.setText(movie.getDateOfFunction());
        holder.timeOfFunctions.setText(movie.getTimeOfFunction());
        holder.Venue.setText(movie.getLocation());
    }

    @Override
    public int getItemCount() {
        return funtionsList.size();
    }
}
