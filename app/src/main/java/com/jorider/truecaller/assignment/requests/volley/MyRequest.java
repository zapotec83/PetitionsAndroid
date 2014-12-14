package com.jorider.truecaller.assignment.requests.volley;

import java.util.Map;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class MyRequest extends Request<NetworkResponse> {

	private Listener<NetworkResponse> mListener = null;
	private Map<String, String> mParams;
	private Map<String, String> mHeaders;

	public MyRequest(int method, String url, Listener<NetworkResponse> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		this.mListener = listener;
	}

	@Override
	protected void deliverResponse(NetworkResponse response) {
		mListener.onResponse(response);
	}

	@Override
	protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
		return Response.success(response,HttpHeaderParser.parseCacheHeaders(response));
	}
}