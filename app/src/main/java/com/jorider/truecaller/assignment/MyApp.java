package com.jorider.truecaller.assignment;

import android.app.Application;

import com.jorider.truecaller.assignment.requests.volley.MyVolley;

/**
 * Created by jorge on 13/12/14.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyVolley.init(this);
    }
}
