package com.jorider.truecaller.assignment.listeners;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

/**
 * Created by jorge on 13/12/14.
 */
public interface ListenerVolley {

    public void onResponseOK (NetworkResponse response);

    public void onResponseKO (VolleyError error);
}
