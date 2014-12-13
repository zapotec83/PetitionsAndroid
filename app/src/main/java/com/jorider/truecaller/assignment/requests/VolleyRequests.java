package com.jorider.truecaller.assignment.requests;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jorider.truecaller.assignment.constants.Constants;
import com.jorider.truecaller.assignment.listeners.ListenerVolley;
import com.jorider.truecaller.assignment.volley.MyVolley;

/**
 * Created by jorge on 13/12/14.
 */
public class VolleyRequests {

    /**
     * Make request to server
     *
     * @param method        int
     * @param timeout 		int
     * @param impl 			ListenerVolley
     */
    public void createVolleyRequest(int method, int timeout, final ListenerVolley impl) {

        StringRequest networkRequest = new StringRequest(method, Constants.URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {
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
