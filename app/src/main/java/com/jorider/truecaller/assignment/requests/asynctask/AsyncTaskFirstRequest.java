package com.jorider.truecaller.assignment.requests.asynctask;

import android.os.AsyncTask;

import com.jorider.truecaller.assignment.assignments.ManageAssignments;
import com.jorider.truecaller.assignment.listeners.CallbackFirstRequest;
import com.jorider.truecaller.assignment.model.MyHttpResponse;
import com.jorider.truecaller.assignment.model.Truecaller10thCharacterRequest;
import com.jorider.truecaller.assignment.model.TruecallerRequestError;
import com.jorider.truecaller.assignment.requests.HttpRequests;

import java.io.IOException;

/**
 * Created by jorge
 */
public class AsyncTaskFirstRequest extends AsyncTask<Void, Void, Truecaller10thCharacterRequest> {

    public static final String TAG = AsyncTaskThirdRequest.class.getName();

    private CallbackFirstRequest callback = null;

    public AsyncTaskFirstRequest(CallbackFirstRequest callback) {
        this.callback = callback;
    }

    @Override
    protected Truecaller10thCharacterRequest doInBackground(Void... params) {
        HttpRequests request = new HttpRequests();

        Truecaller10thCharacterRequest responseCallback = new Truecaller10thCharacterRequest();
        MyHttpResponse response = null;
        try {
            response = request.createRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null) {
            if (response.getContent() != null) {
                responseCallback = ManageAssignments.manageFirstResponse(response.getContent());
            }
        } else {
            responseCallback.setError(new TruecallerRequestError(-1, "Unhandled error"));
        }
        return responseCallback;
    }

    @Override
    protected void onPostExecute(Truecaller10thCharacterRequest response) {
        callback.onFirstResult(response);
    }
}
