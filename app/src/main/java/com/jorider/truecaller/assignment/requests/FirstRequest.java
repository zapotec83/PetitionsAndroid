package com.jorider.truecaller.assignment.requests;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.jorider.truecaller.assignment.listeners.ListenerFirstRequest;
import com.jorider.truecaller.assignment.listeners.ListenerVolley;
import com.jorider.truecaller.assignment.model.AppRequestError;
import com.jorider.truecaller.assignment.utils.ManageRequestAnswer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

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
     * @param list
     */
    public FirstRequest(ListenerFirstRequest list){
        this.listener = list;
    }

    @Override
    public void run(int library) {
        switch (library) {
            case NATIVE:
                break;
            case VOLLEY:
                new VolleyRequests().createVolleyRequest(TIMEOUT, this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResponseOK(NetworkResponse networkResponse) {
        listener.onResultOK(ManageRequestAnswer.manageResultFromServer(networkResponse.data));
    }

    @Override
    public void onResponseKO(VolleyError error) {
        AppRequestError appRequestError = ManageRequestAnswer.manageVolleyError(error, TAG);
        listener.onErrorRequest(appRequestError);
    }
}
