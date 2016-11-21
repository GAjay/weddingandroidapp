package com.example.gdjkj.myapplication.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gdjkj.myapplication.ApiClient;
import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.activites.ContainerActivity;
import com.example.gdjkj.myapplication.adapter.MessageAdapter;
import com.example.gdjkj.myapplication.model.Allwishes;
import com.example.gdjkj.myapplication.model.Response;
import com.example.gdjkj.myapplication.utlis.SharedPreference;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by ajay on 21/11/16.
 */

public class RespondFragment extends Fragment {

    View rootView;

    public RespondFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_respond, container, false);
        final SharedPreference sp = new SharedPreference(getActivity());
        Log.d("number", sp.getStorePhoneNumber());

        final RelativeLayout ll = (RelativeLayout) rootView.findViewById(R.id.respond_fragment);
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
        return  rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            getAllwish(rootView);
        }
    }

    public void getAllwish(final View rootView) {
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
        if(allwishes.size()>0) {
            final SharedPreference sp = new SharedPreference(getActivity());
            MessageAdapter adapter = new MessageAdapter(getActivity(), allwishes, sp.getStorePhoneNumber());
            ListView listView = (ListView) rootView.findViewById(R.id.listview_respond);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getActivity(), ContainerActivity.class);
                    intent.putExtra("screenName", 102);
                    startActivityForResult(intent, 102);
                }
            });

        }
        else{
            ListView listView = (ListView) rootView.findViewById(R.id.listview_respond);
            listView.setVisibility(View.GONE);
            TextView textView = (TextView) rootView.findViewById(R.id.empty);
                    textView.setVisibility(View.VISIBLE);
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
}
