package com.jorider.truecaller.assignment.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @author jorge
 */
public class MyVolley {

	public static final String TAG = MyVolley.class.getName();

	private static RequestQueue mRequestQueue = null;

	public static void init(Context context) {

		if(mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(context);
		}
	}

	public static RequestQueue getRequestQueue() {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} else {
			throw new IllegalStateException("RequestQueue not initialized");
		}
	}

}
