package com.haoli.utils;

import com.haoli.ActionBar.NewsListFragment;

import android.support.v4.app.Fragment;

public class WealthCenterFragmentContainer extends FragmentContainerBase {

	
	public NewsListFragment juniorFragment = new NewsListFragment(URLUtil.WC_JUNIOR,null);
	public NewsListFragment middleLevelTrackFragment = new NewsListFragment(URLUtil.WC_MIDDLE_LEVEL,null);
	public NewsListFragment highLevelFragment = new NewsListFragment(URLUtil.WC_HIGH_LEVEL,null);
	public NewsListFragment analysisTechFragment = new NewsListFragment(URLUtil.WC_ANALYSIS_TECH,null);
	public NewsListFragment capTrainingFragment = new NewsListFragment(URLUtil.WC_CAPABILITY_TRAINING,null);

	public WealthCenterFragmentContainer(){
		titles = new String[]{"初级成长", "中级提升", "高级培养","选股教学","实战培训"};
		mTabClassArray = new Fragment[]{
				juniorFragment,
				middleLevelTrackFragment,
				highLevelFragment,
				analysisTechFragment,
				capTrainingFragment
		};
	}
}
