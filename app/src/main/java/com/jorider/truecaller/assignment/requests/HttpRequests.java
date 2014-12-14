package com.jorider.truecaller.assignment.requests;

import android.util.Log;

import com.jorider.truecaller.assignment.constants.Constants;
import com.jorider.truecaller.assignment.model.MyHttpResponse;
import com.jorider.truecaller.assignment.utils.ManageRequestAnswer;

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
import java.io.InputStream;

/**
 * Created by jorge
 */
public class HttpRequests {

    public static final String TAG = HttpRequests.class.getName();

    public final int TIMEOUT = Constants.DEFAULT_TIMEOUT;

    /**
     * Generate request with HttpClient
     *
     * @return  MyHttpResponse
     * @throws  java.io.IOException
     */
    public MyHttpResponse createRequest() throws IOException {

        HttpClient client = generateHttpClient();
        HttpGet request = new HttpGet(Constants.URL);

        /**
         * GENERATE REQUEST
         */
        HttpResponse response = client.execute(request);

        MyHttpResponse myResponse = new MyHttpResponse();

        if (response.getStatusLine().getStatusCode() == 200) {
            String resultServer = "";
            try {
                InputStream stream = response.getEntity().getContent();
                resultServer = ManageRequestAnswer.manageResultFromServer(stream);
            } catch (IOException e) {
                Log.e(TAG, "Error getting answer from server");
            } finally {
                myResponse.setContent(resultServer);
            }
        } else {
            myResponse.setRequestError(ManageRequestAnswer.manageRequestError(response));
        }

        return myResponse;
    }

    /**
     * Generate HttpClient
     *
     * @return
     */
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

        HttpConnectionParams.setConnectionTimeout(params, TIMEOUT);

        return httpClient;
    }

}
