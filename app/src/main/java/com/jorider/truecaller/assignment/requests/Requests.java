package com.jorider.truecaller.assignment.requests;

import android.os.AsyncTask;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.jorider.truecaller.assignment.constants.Constants;
import com.jorider.truecaller.assignment.listeners.ListenerAsyncTask;
import com.jorider.truecaller.assignment.listeners.ListenerRequests;
import com.jorider.truecaller.assignment.listeners.ListenerVolley;
import com.jorider.truecaller.assignment.model.AppRequestError;
import com.jorider.truecaller.assignment.requests.asynctask.AsyncTaskRequest;
import com.jorider.truecaller.assignment.requests.volley.VolleyRequests;
import com.jorider.truecaller.assignment.utils.ManageRequestAnswer;

import org.apache.http.HttpResponse;

/**
 * Created by jorge on 13/12/14.
 */
public class Requests extends BaseRequest implements ListenerVolley, ListenerAsyncTask {

    public static final String TAG = Requests.class.getName();

    public ListenerRequests listener = null;
    public final int TIMEOUT = Constants.DEFAULT_TIMEOUT;

    /**
     * Constructor
     *
     * @param list
     */
    public Requests(ListenerRequests list){
        this.listener = list;
    }

    @Override
    public void run(int library) {
        switch (library) {
            case NATIVE:
                new AsyncTaskRequest(TIMEOUT, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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

    @Override
    public void responseAsyncTask(HttpResponse response) {

    }
}
