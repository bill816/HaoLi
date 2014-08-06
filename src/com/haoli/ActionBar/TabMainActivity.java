package com.haoli.ActionBar;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.haoli.R;
import com.haoli.utils.URLUtil;

/**
 * ActionBar主界面
 * 
 * @author guoxiao
 */
public class TabMainActivity extends FragmentActivity {

	public  final String[] titles = {"操盘必读", "个股追踪", "深度课题","市场攻略"};
	public  NewsListFragment mustReadFragment = new NewsListFragment(URLUtil.MR_MUST_READ,null);
	public  NewsListFragment stocksTrackFragment = new NewsListFragment(URLUtil.MR_STOCKS_TRACK,null);
	public  NewsListFragment deepTopicsFragment = new NewsListFragment(URLUtil.MR_DEEP_TOPICS,null);
	public  NewsListFragment marketStrategyFragment = new NewsListFragment(URLUtil.MR_MARKET_STRATEGY,null);
	
	public ChatFragment aboutFragment1 = new ChatFragment();
	public StocksTrackFragment aboutFragment2 = new StocksTrackFragment();
	public StocksTrackFragment aboutFragment3 = new StocksTrackFragment();
	public StocksTrackFragment aboutFragment4 = new StocksTrackFragment();
	
	public Fragment mTabClassArray[]= {
		mustReadFragment,
		stocksTrackFragment,
		deepTopicsFragment,
		marketStrategyFragment
	};
	
	/**
	 * PagerSlidingTabStrip的实例
	 */
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	/**
	 * 获取当前屏幕的密度
	 */
	private DisplayMetrics dm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setOverflowShowingAlways();
		dm = getResources().getDisplayMetrics();
		pager = (ViewPager) findViewById(R.id.pager);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		pager.setOffscreenPageLimit(0);
		tabs.setViewPager(pager);
		pager.setCurrentItem(0);
		setTabsValue();
	}

	/**
	 * 对PagerSlidingTabStrip的各项属性进行赋值。
	 */
	private void setTabsValue() {
		// 设置Tab是自动填充满屏幕的
		tabs.setShouldExpand(true);
		// 设置Tab的分割线是透明的
		tabs.setDividerColor(Color.TRANSPARENT);
		// 设置Tab底部线的高度
		tabs.setUnderlineHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 1, dm));
		// 设置Tab Indicator的高度
		tabs.setIndicatorHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, dm));
		// 设置Tab标题文字的大小
		tabs.setTextSize((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 16, dm));
		// 设置Tab Indicator的颜色
		tabs.setIndicatorColor(Color.parseColor("#45c01a"));
		// 设置选中Tab文字的颜色 (这是我自定义的一个方法)
		tabs.setSelectedTextColor(Color.parseColor("#45c01a"));
		// 取消点击Tab时的背景色
		tabs.setTabBackground(0);
	}
	public class MyPagerAdapter extends FragmentStatePagerAdapter  {

		private int currentPageIndex = 0;
		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return titles[position];
		}

		@Override
		public void finishUpdate(ViewGroup container) {
			// TODO Auto-generated method stub
			super.finishUpdate(container);
		}

		@Override
		public Object instantiateItem(ViewGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			return super.instantiateItem(arg0, arg1);
		}

		@Override
		public void startUpdate(ViewGroup container) {
			// TODO Auto-generated method stub
			super.startUpdate(container);
		}

		@Override
		public int getCount() {
			return titles.length;
		}
		
		@Override
		public Fragment getItem(int position) {
			return mTabClassArray[position];
			
			
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}
*/
	private void setOverflowShowingAlways() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}