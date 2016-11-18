package com.example.gdjkj.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gdjkj.myapplication.R;

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
        View rootView = inflater.inflate(R.layout.fragment_sendmessage, container, false);
        return rootView;
    }
}
