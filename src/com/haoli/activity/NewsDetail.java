package com.haoli.activity;

import java.util.List;

import com.haoli.R;
import com.haoli.bean.NewsDataBase;
import com.haoli.bean.NewsDataDetail;
import com.haoli.biz.NewsItemBiz;
import com.haoli.net.HaoliRestClient;
import com.haoli.utils.URLUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

public class NewsDetail extends Activity{

	private final static String TAG = "NewsDetail";
	private WebView mWebView;
	private TextView mNewsTitle;
	private TextView mNewsTime;
	private String mNewsID;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail_layout);
        
        Bundle bundle = this.getIntent().getExtras(); 
        mNewsID = bundle.getString("id");
        mWebView = (WebView)findViewById(R.id.wvContent);
        mNewsTitle = (TextView)findViewById(R.id.newsTitle);
        mNewsTime = (TextView)findViewById(R.id.newsTime);
        mWebView.getSettings().setJavaScriptEnabled(true);
        HaoliRestClient.post(URLUtil.NEWS_READ_DETAIL+"id="+mNewsID, null, resphandler);
    }
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	
    AsyncHttpResponseHandler resphandler = new AsyncHttpResponseHandler(){
		@Override
        public void onSuccess(String response) {
	        ;
//	        String data = "<font face='黑体'>（1）万丰奥威上半年净利2亿元 拟10股派5元<br>（2）金正大上半年净利4.79亿元 同比增长27%<br>（3）哈空调拟1.31亿底价售上海天勃100%股权<br>（4）江铃汽车7月汽车销量22031辆 同比增33%<br>（5）维尔利拟2590万元受让常州大维51%股权<br>（6）信质电机上半年净利8069万 拟10股转5股<br>（7）三星电气中期净利1.03亿 同比增22.88%<br>（8）复星医药投5亿元与公立医院合作<br>（9）长园集团利好频发 沃尔核材举牌浮盈约3000万<br>（10）明牌珠宝进军在线教育 前景不明</font>";
//	        String data ="<p><img style='width: 439px; height: 367px;' src='http://115.28.135.82/UploadFiles/article/20148513512483.jpg' width='439' height='421'></p><p><img style='width: 439px; height: 415px;' src='http://115.28.135.82/UploadFiles/article/20148513543758.jpg' width='439' height='463'></p><p><img style='width: 439px; height: 391px;' src='http://115.28.135.82/UploadFiles/article/20148513650954.jpg' width='439' height='443'></p><p><img src='http://115.28.135.82/UploadFiles/article/20148513724802.jpg' width='439' height='447'></p>";
	        NewsDataDetail detail = NewsItemBiz.JsonStrToNewsDetail(response);
	        mNewsTitle.setText(detail.getNewsTitle());
	        mNewsTime.setText(detail.getNewsTime());
	        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
	        mWebView.loadDataWithBaseURL(null, detail.getNewsContent(), "text/html","utf-8", null);
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