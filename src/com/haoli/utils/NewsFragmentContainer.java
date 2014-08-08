package com.haoli.utils;

import com.haoli.ActionBar.NewsListFragment;

import android.support.v4.app.Fragment;

public class NewsFragmentContainer extends FragmentContainerBase {

	
	public NewsListFragment mustReadFragment = new NewsListFragment(URLUtil.MR_MUST_READ,null);
	public NewsListFragment StocksTrackFragment = new NewsListFragment(URLUtil.MR_STOCKS_TRACK,null);
	public NewsListFragment deepTopicsFragment = new NewsListFragment(URLUtil.MR_DEEP_TOPICS,null);
	public NewsListFragment marketStrategyFragment = new NewsListFragment(URLUtil.MR_MARKET_STRATEGY,null);
	public NewsListFragment investMarrowFragment = new NewsListFragment(URLUtil.SP_INVEST_MARROW,null);
	public NewsListFragment exclusivRevealedFragment = new NewsListFragment(URLUtil.SP_EXCLUSIV_REVEALED,null);
	public NewsListFragment confidentialTellFragment = new NewsListFragment(URLUtil.SP_CONFIDENTIAL_TELL,null);

	public NewsFragmentContainer(){
		titles = new String[]{"操盘必读", "个股追踪", "深度课题","市场攻略","投资精要 ","独家揭密","机密内参"};
		mTabClassArray = new Fragment[]{
				mustReadFragment,
				StocksTrackFragment,
				deepTopicsFragment,
				marketStrategyFragment,
				investMarrowFragment,
				exclusivRevealedFragment,
				confidentialTellFragment
		};
	}
}
