package com.example.gdjkj.myapplication.fragments;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gdjkj.myapplication.R;

/**
 * Created by gdjkj on 11/6/16.
 */
public class infoFragment extends Fragment implements View.OnClickListener {

    //http://www.hindustantimes.com/rf/image_size_800x600/HT/p2/2016/05/26/Pictures/_65d9bb82-2327-11e6-bd64-8acd98c1ae00.jpg
    public infoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.header_cover_image);
        rootView.findViewById(R.id.contact_number).setOnClickListener(this);
        rootView.findViewById(R.id.contact_number2).setOnClickListener(this);
        rootView.findViewById(R.id.imageView_contact).setOnClickListener(this);
        rootView.findViewById(R.id.imageView_contact2).setOnClickListener(this);
        rootView.findViewById(R.id.textView_whatsup).setOnClickListener(this);
        rootView.findViewById(R.id.imageView_contact_whatsup).setOnClickListener(this);
        Glide.with(getContext())
                .load("https://lh3.googleusercontent.com/FryrXB6ZujdjjqTiG6KOG46cxkJve4SnLZMymU3Bvi8aT7Xhlo9iilFO_WhuuullqAebbfbAH3cxLQBceeTOxODlw3qKG0shxozQsmIbYM4toi59rWi7VSn2ISHIajCfrYNu")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.splash_color_cream)
                .into(imageView);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_number:
                dialer("9414217825");
                break;
            case R.id.contact_number2:
                dialer("9460780288");
                break;
            case R.id.imageView_contact:
                dialer("9414217825");
                break;
            case R.id.imageView_contact2:
                dialer("9460780288");
                break;
            case R.id.textView_whatsup:
                openWhatsApp();
                break;
            case R.id.imageView_contact_whatsup:
                openWhatsApp();
                break;
        }
    }

    public void dialer(String number){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        startActivity(intent);
    }
//    public void whatsup(){
//        PackageManager pm = getActivity().getPackageManager();
//
//        try
//        {
//            // Raise exception if whatsapp doesn't exist
//            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
////919784949989
//          /*  Intent sendIntent = new Intent(Intent.ACTION_SEND);
//            sendIntent.setType("text/plain");
//            sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
//            sendIntent.putExtra("jid","+91 9620901367"+"@s.whatsapp.net");
//            sendIntent.putExtra(Intent.EXTRA_TEXT, "Congratulations :)");
//            startActivity(sendIntent);*/
//            Intent waIntent = new Intent(Intent.ACTION_SEND);
//            waIntent.putExtra("jid","content://com.android.contacts/data/9620901367"+"@s.whatsapp.net");
//            waIntent.setType("text/plain");
//            waIntent.setPackage("com.whatsapp");
//            waIntent.putExtra(Intent.EXTRA_TEXT, "Congratulations :)");
//            startActivity(waIntent);
//        }
//        catch (PackageManager.NameNotFoundException e)
//        {
//            Toast.makeText(getActivity(), "Please install whatsapp app", Toast.LENGTH_SHORT)
//                    .show();
//        }
//    }

    private void openWhatsApp() {
        String smsNumber = "919784949989";
        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
        if (isWhatsappInstalled) {

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix

            startActivity(sendIntent);
        } else {
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            Toast.makeText(getActivity(), "WhatsApp not Installed",
                    Toast.LENGTH_SHORT).show();
            startActivity(goToMarket);
        }
    }

    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getActivity().getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}