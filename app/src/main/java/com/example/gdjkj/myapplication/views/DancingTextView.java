package com.example.gdjkj.myapplication.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by gdjkj on 10/24/16.
 */

public class DancingTextView extends TextView {
    public DancingTextView(Context context) {
        super(context);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Satisfy-Regular.ttf");
        setTypeface(tf);
    }
    public DancingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Satisfy-Regular.ttf");
        setTypeface(tf);
    }
    public DancingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Satisfy-Regular.ttf");
        setTypeface(tf);
    }
}
