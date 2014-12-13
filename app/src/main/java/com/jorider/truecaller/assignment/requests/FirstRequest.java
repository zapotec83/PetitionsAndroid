package com.jorider.truecaller.assignment.requests;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.jorider.truecaller.assignment.listeners.ListenerFirstRequest;
import com.jorider.truecaller.assignment.listeners.ListenerVolley;

/**
 * Created by jorge on 13/12/14.
 */
public class FirstRequest extends BaseRequest implements ListenerVolley {

    public static final String TAG = FirstRequest.class.getName();

    public ListenerFirstRequest listener = null;
    public final int TIMEOUT = 15000;

    /**
     * Constructor
     *
     * @param library
     * @param list
     */
    public FirstRequest(int library, ListenerFirstRequest list){

        this.listener = list;

        switch (library) {
            case NATIVE:
                break;
            case VOLLEY:
                new VolleyRequests().createVolleyRequest(Request.Method.POST, TIMEOUT, this);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {

    }

    @Override
    public void onResponseOK(String response) {
        listener.onResultOK(response);
    }

    @Override
    public void onResponseKO(VolleyError error) {
        int httpResponse = 404;
        String message = null;

        if (error instanceof NetworkError) {
            httpResponse = 403;
            message = (error == null || error.getCause() == null || error.getCause().toString() == null) ? "NetworkError" : error.getCause().toString();
        } else if (error instanceof ServerError) {
            httpResponse = 500;
            message = (error == null || error.getCause() == null || error.getCause().toString() == null) ? "ServerError" : error.getCause().toString();
        } else if (error instanceof AuthFailureError) {
            httpResponse = 511;
            message = (error == null || error.getCause() == null || error.getCause().toString() == null) ? "AuthFailureError" : error.getCause().toString();
        } else if (error instanceof ParseError) {
            message = (error == null || error.getCause() == null || error.getCause().toString() == null) ? "ParseError" : error.getCause().toString();
        } else if (error instanceof TimeoutError) {
            httpResponse = 408;
            message = (error == null || error.getCause() == null || error.getCause().toString() == null) ? "TimeoutError" : error.getCause().toString();
        } else {
            try {
                httpResponse = error.networkResponse.statusCode;

                message = error.getCause().toString();
                if(message == null) {
                    message = error.getMessage();
                    if(message == null) {
                        error.getStackTrace().toString();
                    }
                }
            } catch (Throwable e) {
                Log.e(TAG, (e == null || e.getLocalizedMessage() == null) ? "Throwable" : e.getLocalizedMessage());
                message = "";
            }
        }

        listener.onErrorRequest(httpResponse, message);

    }
}
