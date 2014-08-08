package com.haoli.utils;

import com.haoli.activity.StocksList;
import com.haoli.activity.TabMainActivity;

public class TabContainer {

	public static String mTextviewArray[] = {"市场研究", "财富中心", "专案模型", "豪俪之家"};
	
	
	public static int typeArray[]={
		Constant.MODULES_TYPE_MR,
		Constant.MODULES_TYPE_SP,
		Constant.MODULES_TYPE_PM,
		Constant.MODULES_TYPE_HOME
	};
	
	private static NewsFragmentContainer newsFragmentContain = new NewsFragmentContainer();
	private static StockFragmentContainer  stockFragmentContain = new StockFragmentContainer();
	private static WealthCenterFragmentContainer  wealthCenterFragmentContainer = new WealthCenterFragmentContainer();
	private static HaoliHomeFragmentContainer  haoliHomeFragmentContainer = new HaoliHomeFragmentContainer();
	public static FragmentContainerBase FragmentContainArray[]={
		newsFragmentContain,
		wealthCenterFragmentContainer,
		stockFragmentContain,
		haoliHomeFragmentContainer
	};
	
}
