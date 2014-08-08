package com.haoli.ActionBar;

import org.apache.http.cookie.Cookie;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.haoli.R;
import com.haoli.activity.LoginActivity;
import com.haoli.utils.URLUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

/**
 * 发现Fragment的界面
 *  
 * @author guoxiao
 */
public class RedStockFragment extends Fragment {	
	
	private WebView mWebView;
	private int mStockType=0; //0 hongyihao 1 lanyihao
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.stock_webview_layout, container,false);
		
		return view;
	}
	
	public RedStockFragment(int type){
		mStockType = type;
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		mWebView = (WebView) view.findViewById(R.id.wvStockList);
		showWeb();
		
	}
	private void showWeb(){
		mWebView.getSettings().setBuiltInZoomControls(true);  
        mWebView.getSettings().setJavaScriptEnabled(true);
        
        mWebView.setWebViewClient(new WebViewClient(){
        	@Override
        	public boolean shouldOverrideUrlLoading(WebView view,String url){
        		return false;
        	}
        });
        
        if (! LoginActivity.myCookieStore.getCookies().isEmpty()){  
            CookieSyncManager.createInstance(getActivity());  
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true); 
            cookieManager.removeSessionCookie();//移除
           
                //sync all the cookies in the httpclient with the webview by generating cookie string  
            for (Cookie cookie : LoginActivity.myCookieStore.getCookies()){  
                String cookieString = cookie.getName() + "=" + cookie.getValue() + "; domain=" + cookie.getDomain();  
                cookieManager.setCookie(URLUtil.BASE_URL + URLUtil.SP_RED_STOCKS_P, cookieString); 
                CookieSyncManager.getInstance().sync();
            }  
        }
        if(0 == mStockType){
        	mWebView.loadUrl(URLUtil.BASE_URL + URLUtil.SP_RED_STOCKS_P);
        }else{
        	mWebView.loadUrl(URLUtil.BASE_URL + URLUtil.SP_BLUE_STOCKS_P);
        }
	}
}
