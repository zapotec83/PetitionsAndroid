package com.jorider.truecaller.assignment.requests;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.jorider.truecaller.assignment.listeners.ListenerSecondRequest;
import com.jorider.truecaller.assignment.listeners.ListenerVolley;
import com.jorider.truecaller.assignment.model.AppRequestError;
import com.jorider.truecaller.assignment.utils.ManageRequestAnswer;

/**
 * Created by jorge
 */
public class SecondRequest extends BaseRequest implements ListenerVolley {

    public static final String TAG = SecondRequest.class.getName();

    public ListenerSecondRequest listener = null;
    public final int TIMEOUT = 15000;

    /**
     * Constructor
     *
     * @param list
     */
    public SecondRequest(ListenerSecondRequest list){
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
