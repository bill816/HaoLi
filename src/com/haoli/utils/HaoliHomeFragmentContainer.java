package com.haoli.utils;

import com.haoli.ActionBar.NewsListFragment;

import android.support.v4.app.Fragment;

public class HaoliHomeFragmentContainer extends FragmentContainerBase {

	
	public NewsListFragment coursePushFragment = new NewsListFragment(URLUtil.HH_COURSE_PUSH,null);
	public NewsListFragment lastestActivityFragment = new NewsListFragment(URLUtil.HH_LATEST_ACTIVITY,null);
	public NewsListFragment venuShowFragment = new NewsListFragment(URLUtil.HH_VENU_SHOW,null);
	public NewsListFragment senseLifeFragment = new NewsListFragment(URLUtil.HH_SENSE_LIFE,null);
	public NewsListFragment specialTalkFragment = new NewsListFragment(URLUtil.HH_SPECIAL_TALK,null);

	public HaoliHomeFragmentContainer(){
		titles = new String[]{"课程推送", "最新活动", "会场展示","人生感悟","百味杂谈"};
		mTabClassArray = new Fragment[]{
				coursePushFragment,
				lastestActivityFragment,
				venuShowFragment,
				senseLifeFragment,
				specialTalkFragment
		};
	}
}
