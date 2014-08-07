package com.haoli.net;
// Static wrapper library around AsyncHttpClient

import com.haoli.utils.URLUtil;
import com.loopj.android.http.*;

public class HaoliRestClient {

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
    public static void setCookieStore(PersistentCookieStore store){
    	client.setCookieStore(store);
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return URLUtil.BASE_URL + relativeUrl;
    }
}