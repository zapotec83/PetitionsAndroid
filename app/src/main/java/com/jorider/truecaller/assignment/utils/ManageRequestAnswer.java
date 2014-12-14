package com.jorider.truecaller.assignment.utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.jorider.truecaller.assignment.model.AppRequestError;

import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

/**
 * Created by jorge
 */
public class ManageRequestAnswer {

    public static final String TAG = ManageRequestAnswer.class.getName();

    /**
     * Method to manage the Volley given error
     *
     * @param error
     * @return
     */
    public static AppRequestError manageVolleyError(VolleyError error) {

        AppRequestError appError = new AppRequestError();
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

        appError.setHttpCode(httpResponse);
        appError.setMsg(message);

        return appError;
    }

    /**
     * Manage result from request
     *
     * @param source        InputStream
     * @return
     */
    public static String manageResultFromServer(InputStream source) {

        String respuesta = "";
        try {

            Reader reader = new InputStreamReader(source);

            BufferedReader in = new BufferedReader(reader);
            String line = null;
            StringBuilder rslt = new StringBuilder();
            while ((line = in.readLine()) != null) {
                rslt.append(line);
            }
            respuesta = rslt.toString();

        } catch (Exception e) {
        }
        return respuesta;
    }

    /**
     * Manage the error response from the AsyncTask request
     *
     * @param response
     * @return
     */
    public static AppRequestError manageAsyncTaskError(HttpResponse response) {
        AppRequestError appRequestError = new AppRequestError();
        appRequestError.setHttpCode(response.getStatusLine().getStatusCode());
        appRequestError.setMsg(response.getStatusLine().getReasonPhrase());
        return appRequestError;
    }
}
