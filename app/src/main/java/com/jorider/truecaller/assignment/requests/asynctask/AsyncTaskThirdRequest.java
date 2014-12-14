package com.jorider.truecaller.assignment.requests.asynctask;

import android.os.AsyncTask;

import com.jorider.truecaller.assignment.assignments.ManageAssignments;
import com.jorider.truecaller.assignment.constants.Constants;
import com.jorider.truecaller.assignment.listeners.CallbackThirdRequest;
import com.jorider.truecaller.assignment.model.MyHttpResponse;
import com.jorider.truecaller.assignment.model.TruecallerRequestError;
import com.jorider.truecaller.assignment.model.TruecallerWordCounterRequest;
import com.jorider.truecaller.assignment.requests.HttpRequests;

import java.io.IOException;

/**
 * Created by jorge
 */
public class AsyncTaskThirdRequest extends AsyncTask<Void, Void, TruecallerWordCounterRequest> {

    public static final String TAG = AsyncTaskThirdRequest.class.getName();

    private CallbackThirdRequest callback = null;

    public AsyncTaskThirdRequest(CallbackThirdRequest callback) {
        this.callback = callback;
    }

    @Override
    protected TruecallerWordCounterRequest doInBackground(Void... params) {
        HttpRequests request = new HttpRequests();
        TruecallerWordCounterRequest responseCallback = new TruecallerWordCounterRequest();
        MyHttpResponse response = null;
        try {
            response = request.createRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            if(response.getContent() != null) {
                responseCallback = ManageAssignments.manageThirdResponse(response.getContent());
            }
        } else {
            responseCallback.setError(new TruecallerRequestError(-1, "Unhandled error"));
        }
        return responseCallback;
    }

    @Override
    protected void onPostExecute(TruecallerWordCounterRequest response) {
        callback.onThirdResult(response);
    }

}
