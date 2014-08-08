package com.haoli.utils;

import com.haoli.ActionBar.BlueStockFragment;
import com.haoli.ActionBar.RedStockFragment;
import android.support.v4.app.Fragment;

public class StockFragmentContainer extends FragmentContainerBase {

	
	public RedStockFragment redStockFragment = new RedStockFragment(0);
	public BlueStockFragment blueStockFragment = new BlueStockFragment();
	public StockFragmentContainer(){
		titles = new String[]{"红一号", "蓝一号"};
		mTabClassArray = new Fragment[]{
				redStockFragment,
				blueStockFragment
		};
	}
}
