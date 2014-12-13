package com.jorider.truecaller.assignment.requests;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jorider.truecaller.assignment.constants.Constants;
import com.jorider.truecaller.assignment.listeners.ListenerVolley;
import com.jorider.truecaller.assignment.volley.MyRequest;
import com.jorider.truecaller.assignment.volley.MyVolley;

/**
 * Created by jorge
 */
public class VolleyRequests {

    /**
     * Make request to server
     *
     * @param timeout 		int
     * @param impl 			ListenerVolley
     */
    public void createVolleyRequest(int timeout, final ListenerVolley impl) {

        MyRequest networkRequest = new MyRequest(Request.Method.GET, Constants.URL, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse arg0) {
                impl.onResponseOK(arg0);
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
