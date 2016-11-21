package com.example.gdjkj.myapplication.fragments;

import android.app.Activity;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.gdjkj.myapplication.ApiClient;
import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.model.request.SendMessageRequest;
import com.example.gdjkj.myapplication.model.request.SendReplyMessage;
import com.example.gdjkj.myapplication.utlis.SharedPreference;
import com.example.gdjkj.myapplication.utlis.Utlis;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by ajay on 21/11/16.
 */
public class ReplymessageFragment extends Fragment {

    View rootView;
    String Entityid;
    public ReplymessageFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_reply, container, false);
       Entityid = getActivity().getIntent().getExtras().getString("entity_id");
        final LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.replymessage);
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
        Button button = (Button)rootView.findViewById(R.id.reply_wisher);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replyWish(rootView);
            }
        });
        return rootView;
    }

    public void replyWish(final View rootView) {
        Utlis.hideKeyBoard(rootView,getActivity());
        final SharedPreference sp = new SharedPreference(getActivity());
        EditText replyEdittext = (EditText) rootView.findViewById(R.id.edit_reply);

        String Valu_wish = replyEdittext.getText().toString();
        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMessage("Replying Message!");
        progress.setIndeterminate(true);
        progress.setProgress(10);
        progress.show();
        // will call web service here.
        ApiClient.ApiInterface apiService = ApiClient.getApiCall();
        SendReplyMessage sendReplyMessage = new SendReplyMessage(Entityid,Valu_wish);
        Call<ResponseBody> call = apiService.sendReply(sendReplyMessage);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (null != progress) {
                                progress.dismiss();
                                Intent intent=new Intent();
                                getActivity().setResult(Activity.RESULT_OK,intent);
                                getActivity().finish();//finishing activity
                            }
                        }
                    }, 3000);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (null != progress) {
                    progress.dismiss();
                    handleWebserviceFailureResponse(t,rootView);}
            }
        });

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
                    replyWish(rootView);
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
