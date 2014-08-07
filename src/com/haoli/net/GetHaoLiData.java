package com.haoli.net;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;


public class GetHaoLiData {

	public int isLoggined = 0;
	
	public GetHaoLiData(){

	}
	
	public static void login(String usr,String pwd,AsyncHttpResponseHandler resphandler,PersistentCookieStore cookieStore){
			
		RequestParams params = new RequestParams();  
		params.put("username", usr);
		params.put("password", pwd);
		params.put("verifycode", "");
		params.put("toploginbtn", "");
		params.put("ast", "in");
		params.put("coolgn", "yes");
		HaoliRestClient.addHeader();
		HaoliRestClient.setCookieStore(cookieStore);
		HaoliRestClient.post("/module/member/huiyuanzq.asp", params, resphandler);
	}
	
	public void testBidu(){
		//module/shichangyanjiu/caopangbidu.asp
//		HaoliRestClient.post("/module/member/huiyuanzq.asp", null, resphandler);
	}
	

	AsyncHttpResponseHandler resphandler1 = new AsyncHttpResponseHandler(){
		@Override
        public void onSuccess(String response) {
            System.out.println(response);
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
