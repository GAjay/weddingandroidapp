package com.example.gdjkj.myapplication.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gdjkj.myapplication.ApiClient;
import com.example.gdjkj.myapplication.R;
import com.example.gdjkj.myapplication.model.Allwishes;
import com.example.gdjkj.myapplication.model.Response;
import com.example.gdjkj.myapplication.model.request.SendMessageRequest;
import com.example.gdjkj.myapplication.utlis.SharedPreference;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by ajay on 18/11/16.
 */
public class SendmessageFragment extends Fragment {

    public SendmessageFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Send Message");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Send Message");
        final View rootView = inflater.inflate(R.layout.fragment_sendmessage, container, false);
        Button button = (Button) rootView.findViewById(R.id.send_wisher);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendWish(rootView);
            }
        });

        return rootView;
    }

    public void sendWish(final View rootView) {
        final SharedPreference sp = new SharedPreference(getActivity());
        EditText name_editText = (EditText) rootView.findViewById(R.id.edit_name);
        EditText wish_editText = (EditText) rootView.findViewById(R.id.edit_wish);
        String value_name_wisher = name_editText.getText().toString();
        String value_wish_wisher = wish_editText.getText().toString();
        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMessage("Sending Message!");
        progress.setIndeterminate(true);
        progress.setProgress(10);
        progress.show();
        String tokennumber= name_editText.getText()+"15556"+wish_editText.getText();
        String number= sp.getStorePhoneNumber();
        // will call web service here.
        ApiClient.ApiInterface apiService = ApiClient.getApiCall();
        SendMessageRequest sendMessageRequest = new SendMessageRequest(value_name_wisher,number,value_wish_wisher
                ,tokennumber);
        Call<ResponseBody> call = apiService.sendMessage(sendMessageRequest);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (null != progress) {
                                progress.dismiss();
                                System.out.println(response.body().toString());
                            }
                        }
                    }, 3000);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
