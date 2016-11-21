package com.example.gdjkj.myapplication.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.example.gdjkj.myapplication.ApiClient;
import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.activites.ContainerActivity;
import com.example.gdjkj.myapplication.adapter.MessageAdapter;
import com.example.gdjkj.myapplication.model.Allwishes;
import com.example.gdjkj.myapplication.model.Response;
import com.example.gdjkj.myapplication.utlis.SharedPreference;
import com.example.gdjkj.myapplication.utlis.Utlis;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by gdjkj on 10/24/16.
 */

public class ContactFragment extends Fragment {

    View rootView;

    public ContactFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        final SharedPreference sp = new SharedPreference(getActivity());
        Log.d("number", sp.getStorePhoneNumber());
        final Button button = (Button) rootView.findViewById(R.id.editText__phone_submit);

        final RelativeLayout ll = (RelativeLayout) rootView.findViewById(R.id.contact_fragment);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .build();
        int deviceWidth = getResources().getDisplayMetrics().widthPixels;
        int deviceHeight = getResources().getDisplayMetrics().heightPixels;
        ImageSize targetSize = new ImageSize(deviceWidth, deviceHeight);
        imageLoader.loadImage("drawable://" + R.drawable.ic_bg, targetSize, options,
                new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        // Do whatever you want with Bitmap
                        BitmapDrawable background = new BitmapDrawable(loadedImage);
                        ll.setBackgroundDrawable(background);
                    }
                });

        return rootView;
    }


    @Override
    public void setUserVisibleHint(boolean menuVisible) {
        super.setUserVisibleHint(menuVisible);

        if (menuVisible) {
            Log.d("inside", "inside");
            final Button button = (Button) rootView.findViewById(R.id.editText__phone_submit);
            final SharedPreference sp = new SharedPreference(getActivity());
            if (TextUtils.isEmpty(sp.getStorePhoneNumber())) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkFieldValidation(rootView)) {
                            final String phonenumber = ((EditText) rootView.
                                    findViewById(R.id.editText_phone)).getText().toString();
                            System.out.println(phonenumber);
                            sp.storePhoneNumber(phonenumber);
                            EditText editText = (EditText) rootView.findViewById(R.id.editText_phone);
                            editText.setVisibility(View.GONE);
                            button.setVisibility(View.GONE);
                            getAllwish(rootView);
                        } else {
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
            } else {
                EditText editText = (EditText) rootView.findViewById(R.id.editText_phone);
                editText.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                getAllwish(rootView);

            }
        }
    }

    public void getAllwish(final View rootView) {
        Utlis.hideKeyBoard(rootView,getActivity());
        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMessage("Loading");
        progress.setIndeterminate(true);
        progress.setProgress(10);
        progress.show();
        // will call web service here.
        ApiClient.ApiInterface apiService = ApiClient.getApiCall();
        Call<Response> call = apiService.callback();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, final retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (null != progress) {
                                progress.dismiss();
                                ArrayList<Allwishes> allwishes = response.body().getAllwishes();
                                setData(rootView, allwishes);
                            }
                        }
                    }, 3000);

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                if (null != progress) {
                    progress.dismiss();
                handleWebserviceFailureResponse(t,rootView);}
            }
        });
    }

    private void setData(View rootView, ArrayList<Allwishes> allwishes) {
        boolean exist = false;
        ArrayList<Allwishes> allwishes1 = new ArrayList();
        final SharedPreference sp = new SharedPreference(getActivity());
        Log.d("Size", String.valueOf(allwishes.size()));
        for (Allwishes allwish : allwishes) {
            Log.d("Getting Number", allwish.getTelephone());
            Log.d("Stored Number", sp.getStorePhoneNumber());
            if (allwish.getTelephone().equals(sp.getStorePhoneNumber())) {
                allwishes1.add(allwish);
                exist = true;
            }
        }
        if (exist) {
            Log.d("exist", String.valueOf(exist));
            MessageAdapter adapter = new MessageAdapter(getActivity(), allwishes1, sp.getStorePhoneNumber());
            ListView listView = (ListView) rootView.findViewById(R.id.list_message);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(adapter);
            Button button2 = (Button) rootView.findViewById(R.id.send_message);
            button2.setVisibility(View.GONE);
            Button button1 = (Button) rootView.findViewById(R.id.send_message_wish);
            button1.setVisibility(View.VISIBLE);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ContainerActivity.class);
                    intent.putExtra("screenName", 102);
                    startActivityForResult(intent, 102);
                }
            });

        } else {
            Log.d("exist", String.valueOf(exist));
            Button button1 = (Button) rootView.findViewById(R.id.send_message);
            button1.setVisibility(View.VISIBLE);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ContainerActivity.class);
                    intent.putExtra("screenName", 102);
                    startActivityForResult(intent, 102);
                }
            });
        }
    }

    /** Method to handle the all situation when web service calling get failed.
     * @param t : Error thrown by network libray in web service calling.
     * @param rootView : root view of the fragment.
     */
    private void handleWebserviceFailureResponse(Throwable t, final View rootView) {
        // checking if error is instance of IO class, then its internet error. other wise
        // some error from server side.
        if (null != t && t instanceof IOException) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Internet connection error. Please try again.");
            alertDialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    getAllwish(rootView);
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        } else {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Something went wrong.");
            alertDialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
    }
    /**
     * checking fields for valid values.
     *
     * @param rootView
     * @return : true or false on the basis of result.
     */
    private boolean checkFieldValidation(View rootView) {
        boolean isValid = true;
        String pin = ((EditText) rootView.findViewById(R.id.editText_phone)).getText().toString();
        if (pin.length() < 10) {
            return false;
        }
        return isValid;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 102) {
            // Make sure the request was successful
            getAllwish(rootView);

        }
    }
}