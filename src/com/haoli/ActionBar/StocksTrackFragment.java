package com.haoli.ActionBar;

import com.haoli.utils.URLUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

/**
 * 发现Fragment的界面
 * 
 * 
 * @author guoxiao
 */
public class StocksTrackFragment extends NewsListFragment {

	public StocksTrackFragment() {
		super();
		// TODO Auto-generated constructor stub
		super.mRelativeUrl = URLUtil.MR_STOCKS_TRACK;
	}

}
