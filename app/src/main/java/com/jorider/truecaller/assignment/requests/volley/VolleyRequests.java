package com.jorider.truecaller.assignment.requests.volley;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jorider.truecaller.assignment.constants.Constants;
import com.jorider.truecaller.assignment.listeners.ListenerVolley;
import com.jorider.truecaller.assignment.requests.volley.MyRequest;
import com.jorider.truecaller.assignment.requests.volley.MyVolley;

/**
 * Created by jorge
 */
public class VolleyRequests {

    public static final String TAG = VolleyRequests.class.getName();

    /**
     * Make request to server
     *
     * @param timeout 		int
     * @param impl 			ListenerVolley
     */
    public void createVolleyRequest(int timeout, final ListenerVolley impl) {

        StringRequest networkRequest = new StringRequest(Request.Method.GET, Constants.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                impl.onResponseOK(response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                impl.onResponseKO(error);
            }
        });

        networkRequest.setRetryPolicy(new DefaultRetryPolicy(timeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyVolley.getRequestQueue().add(networkRequest);
    }
}
