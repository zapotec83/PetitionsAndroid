package com.jorider.truecaller.assignment.requests.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.jorider.truecaller.assignment.assignments.ManageAssignments;
import com.jorider.truecaller.assignment.listeners.CallbackSecondRequest;
import com.jorider.truecaller.assignment.model.MyHttpResponse;
import com.jorider.truecaller.assignment.model.TruecallerEvery10thCharacterRequest;
import com.jorider.truecaller.assignment.model.TruecallerRequestError;
import com.jorider.truecaller.assignment.requests.HttpRequests;

import java.io.IOException;

/**
 * Created by jorge
 */
public class AsyncTaskSecondRequest extends AsyncTask<Void, Void, TruecallerEvery10thCharacterRequest> {

    public static final String TAG = AsyncTaskThirdRequest.class.getName();

    private CallbackSecondRequest callback = null;

    public AsyncTaskSecondRequest(CallbackSecondRequest callback) {
        this.callback = callback;
    }

    @Override
    protected TruecallerEvery10thCharacterRequest doInBackground(Void... params) {
        HttpRequests request = new HttpRequests();

        TruecallerEvery10thCharacterRequest responseCallback = new TruecallerEvery10thCharacterRequest();
        MyHttpResponse response = null;
        try {
            response = request.createRequest();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        if (response != null) {
            if (response.getContent() != null) {
                responseCallback = ManageAssignments.manageSecondResponse(response.getContent());
            }
        } else {
            responseCallback.setError(new TruecallerRequestError(-1, "Unhandled error"));
        }
        return responseCallback;
    }

    @Override
    protected void onPostExecute(TruecallerEvery10thCharacterRequest response) {
        callback.onSecondResult(response);
    }
}
