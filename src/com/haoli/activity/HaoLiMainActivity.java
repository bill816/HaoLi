package com.haoli.activity;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.haoli.R;
import com.haoli.biz.NewsItemBiz;
import com.haoli.biz.StockItemBiz;
import com.haoli.net.GetHaoLiData;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.Bundle;
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
		
		
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab);
//        GetHaoLiData getData = new GetHaoLiData();
        
//        getData.login("", "");
        
//        NewsItemBiz.getNewsDataBase(0, 0, this);
//        NewsItemBiz.getNewsDetail(null);
        //StockItemBiz.htmlStrToNewsDataBase(0,"",this);
        init();
    }

	
	
    private void init()
	{
		m_tabHost = getTabHost();
	
		int count = Constant.mTabClassArray.length;		
		for(int i = 0; i < count; i++)
		{	
			TabSpec tabSpec = m_tabHost.newTabSpec(Constant.mTextviewArray[i]).
													setIndicator(Constant.mTextviewArray[i]).
													setContent(getTabItemIntent(i));
			m_tabHost.addTab(tabSpec);
		}
		
		m_radioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
		m_radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case R.id.main_tab_weixin:
					m_tabHost.setCurrentTabByTag(Constant.mTextviewArray[0]);
					break;
				case R.id.main_tab_address:
					m_tabHost.setCurrentTabByTag(Constant.mTextviewArray[1]);
					break;
				case R.id.main_tab_find_friend:
					m_tabHost.setCurrentTabByTag(Constant.mTextviewArray[2]);
					break;
				case R.id.main_tab_settings:
					m_tabHost.setCurrentTabByTag(Constant.mTextviewArray[3]);
					break;
				}
			}
		});
		
		 ((RadioButton) m_radioGroup.getChildAt(0)).toggle();
	}
	
	private Intent getTabItemIntent(int index)
	{
		Intent intent = new Intent(this, Constant.mTabClassArray[index]);
		
		return intent;
	}
}