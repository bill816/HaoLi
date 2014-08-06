package com.haoli.ActionBar;

import com.haoli.utils.URLUtil;

import android.support.v4.app.Fragment;

public class ActionBarConstant {

	public static final String[] titles = {"操盘必读", "个股追踪", "深度课题","个股追踪"};
	public static NewsListFragment mustReadFragment = new NewsListFragment(URLUtil.MR_MUST_READ,null);
	public static NewsListFragment StocksTrackFragment = new NewsListFragment(URLUtil.MR_STOCKS_TRACK,null);
	public static NewsListFragment deepTopicsFragment = new NewsListFragment(URLUtil.MR_DEEP_TOPICS,null);
	public static NewsListFragment marketStrategyFragment = new NewsListFragment(URLUtil.MR_MARKET_STRATEGY,null);
	
	public static Fragment mTabClassArray[]= {
		mustReadFragment,
		StocksTrackFragment,
		deepTopicsFragment,
		marketStrategyFragment
	};
}
