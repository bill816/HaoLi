package com.haoli.activity;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.haoli.R;
import com.haoli.biz.NewsItemBiz;
import com.haoli.biz.StockItemBiz;
import com.haoli.net.GetHaoLiData;
import com.haoli.umeng.MyPushIntentService;
import com.haoli.utils.TabContainer;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.IUmengUnregisterCallback;
import com.umeng.message.PushAgent;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;


/**
 * 
 * @author guoxiao
 *  
 */
@SuppressWarnings("deprecation")
public class HaoLiMainActivity extends TabActivity {
    /** Called when the activity is first created. */
    
    
	private TabHost		m_tabHost;		
	private RadioGroup  m_radioGroup;
	private PushAgent mPushAgent;	
	private Context mContext;
	private final  String mPageName = "AnalyticsHome";
		
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab);
        mContext = this;
        //umeng push code
		mPushAgent = PushAgent.getInstance(this);
		mPushAgent.onAppStart();
		mPushAgent.enable(mRegisterCallback);
		mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);
		
		//umeng analytics code
		MobclickAgent.setDebugMode(true);
//      SDK在统计Fragment时，需要关闭Activity自带的页面统计，
//		然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
		MobclickAgent.openActivityDurationTrack(false);
//		MobclickAgent.setAutoLocation(true);
//		MobclickAgent.setSessionContinueMillis(1000);
		
		MobclickAgent.updateOnlineConfig(this);
        init();
    }

	
	
    private void init()
	{
		m_tabHost = getTabHost();
	
		int count = TabContainer.mTextviewArray.length;		
		for(int i = 0; i < count; i++)
		{	
			TabSpec tabSpec = m_tabHost.newTabSpec(TabContainer.mTextviewArray[i]);
			tabSpec.setIndicator(TabContainer.mTextviewArray[i]);
			tabSpec.setContent(getTabItemIntent(i));
			m_tabHost.addTab(tabSpec);
		}
		
		m_radioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
		m_radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case R.id.main_tab_weixin:
					m_tabHost.setCurrentTabByTag(TabContainer.mTextviewArray[0]);
					break;
				case R.id.main_tab_address:
					m_tabHost.setCurrentTabByTag(TabContainer.mTextviewArray[1]);
					break;
				case R.id.main_tab_find_friend:
					m_tabHost.setCurrentTabByTag(TabContainer.mTextviewArray[2]);
					break;
				case R.id.main_tab_settings:
					m_tabHost.setCurrentTabByTag(TabContainer.mTextviewArray[3]);
					break;
				}
			}
		});
		
		 ((RadioButton) m_radioGroup.getChildAt(0)).toggle();
	}
	
	private Intent getTabItemIntent(int index)
	{
		Intent intent = new Intent(this, TabMainActivity.class);
		Bundle bundleSimple = new Bundle();
        bundleSimple.putInt("type", index);
        intent.putExtras(bundleSimple);
//		startActivity(intent);
		return intent;
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart( mPageName );
		MobclickAgent.onResume(mContext);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd( mPageName );
		MobclickAgent.onPause(mContext);
	}
	
	
	public Handler handler = new Handler();
	public IUmengRegisterCallback mRegisterCallback = new IUmengRegisterCallback() {
		
		@Override
		public void onRegistered(String registrationId) {
			// TODO Auto-generated method stub
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
//					updateStatus();
				}
			});
		}
	};
	
	public IUmengUnregisterCallback mUnregisterCallback = new IUmengUnregisterCallback() {
		
		@Override
		public void onUnregistered(String registrationId) {
			// TODO Auto-generated method stub
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
//					updateStatus();
				}
			});
		}
	};
	
}