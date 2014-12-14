package com.jorider.truecaller.assignment.utils;

import android.util.Log;

import com.jorider.truecaller.assignment.model.TruecallerRequestError;

import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by jorge
 */
public class ManageRequestAnswer {

    public static final String TAG = ManageRequestAnswer.class.getName();

    /**
     * Manage result from request
     *
     * @param source InputStream
     * @return
     */
    public static String manageResultFromServer(InputStream source) {

        String respuesta = "";
        StringBuilder rslt = new StringBuilder();

        try {

            Reader reader = new InputStreamReader(source);

            BufferedReader in = new BufferedReader(reader);
            String line = null;
            while ((line = in.readLine()) != null) {
                rslt.append(line);
            }
            respuesta = rslt.toString();

        } catch (Exception e) {
            Log.e(TAG, "Error managing answer");
        }
        return respuesta;
    }

    /**
     * Manage the error response from the AsyncTask request
     *
     * @param response
     * @return
     */
    public static TruecallerRequestError manageRequestError(HttpResponse response) {
        TruecallerRequestError appRequestError = new TruecallerRequestError();
        appRequestError.setHttpCode(response.getStatusLine().getStatusCode());
        appRequestError.setMsg(response.getStatusLine().getReasonPhrase());
        return appRequestError;
    }
}
