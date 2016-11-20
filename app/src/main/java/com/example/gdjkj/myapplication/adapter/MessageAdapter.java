package com.example.gdjkj.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.model.Allwishes;

import java.util.ArrayList;


/**
 * Created by gdjkj on 11/20/16.
 */

public class MessageAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    public ArrayList<Allwishes> itemList;
    Context context;
    private String storeNumber;

    public MessageAdapter(Context context, ArrayList<Allwishes> itemList, String storeNumber) {
        this.itemList = itemList;
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.storeNumber = storeNumber;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageAdapter.ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_wish, null);
                holder = new MessageAdapter.ViewHolder();
                holder.message_wisher_text_view = (TextView) convertView.findViewById(R.id.message_wisher);
                holder.name_wisher_text_view = (TextView) convertView.findViewById(R.id.name_wisher);
                holder.reply_text_view = (TextView) convertView.findViewById(R.id.reply);
                //holder.cardView = (CardView) convertView.findViewById(R.id.card_view_wish);
                convertView.setTag(holder);
            } else {
                holder = (MessageAdapter.ViewHolder) convertView.getTag();
            }

            //holder.cardView.setVisibility(View.VISIBLE);
            holder.message_wisher_text_view.setText(itemList.get(position).getWish());
            holder.name_wisher_text_view.setText(itemList.get(position).getName());
            if(!TextUtils.isEmpty(itemList.get(position).getReply())){
                holder.reply_text_view.setText(itemList.get(position).getReply());
            }
            else{
                holder.reply_text_view.setVisibility(View.GONE);
            }


        return convertView;
    }

    private class ViewHolder {
        TextView message_wisher_text_view;
        TextView name_wisher_text_view;
        TextView reply_text_view;
       // CardView cardView;
    }
}

