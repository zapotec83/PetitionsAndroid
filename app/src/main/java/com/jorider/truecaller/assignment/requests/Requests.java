package com.jorider.truecaller.assignment.requests;

import android.os.AsyncTask;

import com.android.volley.VolleyError;
import com.jorider.truecaller.assignment.constants.Constants;
import com.jorider.truecaller.assignment.listeners.ListenerAsyncTask;
import com.jorider.truecaller.assignment.listeners.ListenerRequests;
import com.jorider.truecaller.assignment.listeners.ListenerVolley;
import com.jorider.truecaller.assignment.model.AppRequestError;
import com.jorider.truecaller.assignment.model.MyHttpResponse;
import com.jorider.truecaller.assignment.requests.asynctask.AsyncTaskRequest;
import com.jorider.truecaller.assignment.requests.volley.VolleyRequests;
import com.jorider.truecaller.assignment.utils.ManageRequestAnswer;

/**
 * Created by jorge
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
                new AsyncTaskRequest(TIMEOUT, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                break;
        }
    }

    @Override
    public void onResponseOK(String response) {
        listener.onResultOK(response);
    }

    @Override
    public void onResponseKO(VolleyError error) {
        AppRequestError appRequestError = ManageRequestAnswer.manageVolleyError(error);
        listener.onErrorRequest(appRequestError);
    }

    @Override
    public void responseAsyncTask(MyHttpResponse response) {
        if(response.getContent() == null) {
            listener.onErrorRequest(response.getError());
        } else {
            listener.onResultOK(response.getContent());
        }
    }
}
