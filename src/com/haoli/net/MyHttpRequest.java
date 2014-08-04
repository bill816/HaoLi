package com.haoli.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class MyHttpRequest {
	
	public static final String  REQUEST_ERROR = "REQUEST_ERROR";
	private HttpClient httpClient;
	private HttpGet httpGet;
	private HttpResponse httpResponse;
	private String strResponse = null;
	public  int errorCode; 
	
	public MyHttpRequest() {
		httpClient = new DefaultHttpClient();
		httpGet = new HttpGet();
	}
	public MyHttpRequest(String url) {
		httpClient = new DefaultHttpClient();
		httpGet = new HttpGet();
	}
	public String getHttp(String url){
		try{
			strResponse = "REQUEST_ERROR";
			httpGet.setURI(URI.create(url));
			httpResponse = httpClient.execute(httpGet);
			
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if(statusCode == HttpStatus.SC_OK){
				
				Log.v("[gx]", "get information ok 200");
				Log.v("[gx]", "get information content length:" + httpResponse.getEntity().getContentLength());
				Log.v("[gx]", "get information content type:" + httpResponse.getEntity().getContentType().getName());
				Log.v("[gx]", "get information content type value:" + httpResponse.getEntity().getContentType().getValue());
				
				
				Log.v("[gx]", "get information content uri scheme:" + httpGet.getURI().getScheme());
				Log.v("[gx]", "get information content uri host:" + httpGet.getURI().getHost());
				Log.v("[gx]", "get information content uri port:" + httpGet.getURI().getPort());
				Log.v("[gx]", "get information content uri path:" + httpGet.getURI().getPath());
				
				
				Log.v("[gx]", "get information content all headers:" + httpResponse.getAllHeaders().length);
				Header[] header =  httpResponse.getAllHeaders();
				for(int i=0; i < httpResponse.getAllHeaders().length; i ++)
				{
					Log.v("[gx]", "get information content header "+ i + " name:" + header[i].getName());
					Log.v("[gx]", "get information content header " + i + " value:" + header[i].getValue());
				}
				
//				Log.v("[GX]", "get information content encoding:" + httpResponse.getEntity().getContentEncoding().getName());
				
				strResponse = EntityUtils.toString(httpResponse.getEntity());
				Log.v("[GX]","response string:"+strResponse);
			}else{
				errorCode = statusCode;
				Log.v("[GX]", "get information error code:" + statusCode);
			}
		}catch(Exception e){
			Log.v("[GX]", "get information exception:" + e);
		}
		return strResponse;
	}
	public boolean urlIsExsit(String url)
	{
		try {
			URL myUrl = new URL(url);
			myUrl.openConnection();
			return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
