package com.haoli.net;
// Static wrapper library around AsyncHttpClient

import com.loopj.android.http.*;

public class HaoliRestClient {
    private static final String BASE_URL = "http://115.28.135.82";

    private static AsyncHttpClient client = new AsyncHttpClient();
    
    public static void addHeader(){
    	//键	值Content-Type	application/x-www-form-urlencoded
    	//键	值
    	//Referer	http://115.28.135.82/index.asp
    	//
    	client.addHeader("Content-Type", "application/x-www-form-urlencoded");
    	client.addHeader("Referer", "http://115.28.135.82/index.asp");
    }
    
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}