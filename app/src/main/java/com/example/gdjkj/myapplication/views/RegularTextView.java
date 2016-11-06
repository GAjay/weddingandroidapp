package com.example.gdjkj.myapplication.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by gdjkj on 10/24/16.
 */

public class RegularTextView extends TextView {
    public RegularTextView(Context context) {
        super(context);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Montserrat-Regular.ttf");
        setTypeface(tf);
    }
    public RegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Montserrat-Regular.ttf");
        setTypeface(tf);
    }
    public RegularTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Montserrat-Regular.ttf");
        setTypeface(tf);
    }
}
