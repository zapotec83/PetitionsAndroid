package com.jorider.truecaller.assignment.requests.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.jorider.truecaller.assignment.constants.Constants;
import com.jorider.truecaller.assignment.listeners.ListenerAsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import java.io.IOException;

/**
 * Created by jorge
 */
public class AsyncTaskRequest extends AsyncTask<Void, Void, HttpResponse> {

    public static final String TAG = AsyncTaskRequest.class.getName();

    private int timeout = Constants.DEFAULT_TIMEOUT;
    private ListenerAsyncTask listener = null;

    public AsyncTaskRequest(int timeout, ListenerAsyncTask listener) {
        this.timeout = timeout;
        this.listener = listener;
    }

    @Override
    protected HttpResponse doInBackground(Void... params) {
        NativeRequest nativeRequest = new NativeRequest();
        HttpResponse response = null;
        try {
            response = nativeRequest.createRequest();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return response;
    }

    @Override
    protected void onPostExecute(HttpResponse response) {
        listener.responseAsyncTask(response);
        super.onPostExecute(response);
    }

    /**
     *
     */
    private class NativeRequest {

        /**
         * Generate request with HttpClient
         *
         * @return
         * @throws IOException
         */
        public HttpResponse createRequest() throws IOException {
            HttpClient client = generateHttpClient();
            HttpGet request = new HttpGet(Constants.URL);
            HttpResponse response = client.execute(request);
            return response;
        }
    }


    public HttpClient generateHttpClient() {

        HttpClient httpClient = new DefaultHttpClient();
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

        HttpParams params = new BasicHttpParams();
        params.setParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, 10);
        params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, new ConnPerRouteBean(10));
        params.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE, false);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

        ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
        httpClient = new DefaultHttpClient(cm, params);

        HttpConnectionParams.setConnectionTimeout(params, timeout);

        return httpClient;
    }
}
