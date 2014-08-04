package com.haoli.net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class GetHaoLiData {

	public int isLoggined = 0;
	
	public GetHaoLiData(){

	}
	
	public void testLogin() throws UnsupportedEncodingException, IOException{
		URL url = new URL("http://115.28.135.82/module/member/huiyuanzq.asp"); //u是要访问网站登录页面的地址  
        URLConnection rulConnection = url.openConnection(); 
        HttpURLConnection connection = (HttpURLConnection) rulConnection;   
        connection.setDoOutput(true); 
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
            
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        out.write("username=guoxiao&password=guoxiao&verifycode=&toploginbtn=&ast=in&coolgn=yes"); 
        out.flush();     
        out.close(); 
         
        String sessionValue = connection.getHeaderField("Set-Cookie");//获取session值  
        String[] sessionId = sessionValue.split(";"); 
        System.out.println(sessionId[0]);
        URL newUrl = new URL("http://115.28.135.82/home.asp");
        URLConnection newConn = newUrl.openConnection(); 
        //newConn.setRequestProperty("Cookie", "ASPSESSIONIDSCQBRSDD=IIAANHKBLJGECNEHDDPMAILD");
        newConn.setRequestProperty("Cookie", sessionId[0]);
        newConn.connect(); 
           
        BufferedInputStream bis = new BufferedInputStream(newConn.getInputStream());
        InputStreamReader inputStreamReader = new InputStreamReader(bis,"gbk");
        BufferedReader br = new BufferedReader(inputStreamReader);
   
        String str = null,s = "";
         
        while ((str = br.readLine()) != null)   
        {  
                System.out.println(str);   
        }
	}
	
	public void login(String usr,String pwd){
			
		RequestParams params = new RequestParams();  
		params.put("username", "guoxiao");
		params.put("password", " ");
		params.put("verifycode", "");
		params.put("toploginbtn", "");
		params.put("ast", "in");
		params.put("coolgn", "yes");
		HaoliRestClient.addHeader(); 
		HaoliRestClient.post("module/member/huiyuanzq.asp", params, resphandler);
	}
	
	public void testBidu(){
		//module/shichangyanjiu/caopangbidu.asp
		HaoliRestClient.post("module/member/huiyuanzq.asp", null, resphandler);
	}
	static int a = 1;
	AsyncHttpResponseHandler resphandler = new AsyncHttpResponseHandler(){
		@Override
        public void onSuccess(String response) {
            System.out.println(response);
            System.out.println("--------------------------------------------");
            System.out.println("--------------------------------------------");
            System.out.println("--------------------------------------------");
            if(a == 1)
            	HaoliRestClient.post("module/celuezhuanan/touzhijingyao.asp", null, resphandler);
            a = 2;
        }
		@Override
        public void onFailure(Throwable e, String response) {
            System.out.println(response);
        }
		
		@Override
        public void onFinish() {
			// Completed the request (either success or failure)
			
        }
	};
}
