package com.example.gdjkj.myapplication.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.activites.ContainerActivity;
import com.example.gdjkj.myapplication.utlis.SharedPreference;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by gdjkj on 10/24/16.
 */

public class ContactFragment extends Fragment {


    public ContactFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        final SharedPreference sp = new SharedPreference(getActivity());
        System.out.println("number"+sp.getStorePhoneNumber());
        final Button button = (Button) rootView.findViewById(R.id.editText__phone_submit);

        final RelativeLayout ll = (RelativeLayout) rootView.findViewById(R.id.contact_fragment);
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



        if(TextUtils.isEmpty(sp.getStorePhoneNumber())) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkFieldValidation(rootView)){
                    final String phonenumber = ((EditText) rootView.findViewById(R.id.editText_phone)).getText().toString();
                    // String pin = ((EditText) findViewById(R.id.text_password)).getText().toString();
                    System.out.println(phonenumber);
                    sp.storePhoneNumber(phonenumber);
                    EditText editText = (EditText) rootView.findViewById(R.id.editText_phone);
                    editText.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                    Button button1 = (Button) rootView.findViewById(R.id.send_message);
                    button1.setVisibility(View.VISIBLE);}
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Not Valid Mobile Number.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //do things
                                        dialog.cancel();
                                    }

                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }

                }
            });
        }
        else{
            EditText editText = (EditText) rootView.findViewById(R.id.editText_phone);
            editText.setVisibility(View.GONE);
            button.setVisibility(View.GONE);

            Button button1 = (Button) rootView.findViewById(R.id.send_message);
            button1.setVisibility(View.VISIBLE);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),ContainerActivity.class);
                    intent.putExtra("screenName",102);
                    startActivity(intent);
                }
            });
        }


        return rootView;
    }

    /**
     * checking fields for valid values.
     *
     * @return : true or false on the basis of result.
     * @param rootView
     */
    private boolean checkFieldValidation(View rootView) {
        boolean isValid = true;
        String pin = ((EditText) rootView.findViewById(R.id.editText_phone)).getText().toString();

        if (pin.length() < 10) {

            return false;
        }
        return isValid;
    }
}