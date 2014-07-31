package com.haoli.ActionBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.haoli.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;

/**
 * 发现Fragment的界面
 * 
 * 
 * @author guoxiao
 */
public class HomeFragment extends ListFragment {
	 
	private PullToRefreshListView list ;
	private int mItemCount = 15;
	private ArrayAdapter mAdapter;
	private LinkedList<String> mListItems;
	private String TAG = HomeFragment.class.getName();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/*
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		FrameLayout fl = new FrameLayout(getActivity());
		fl.setLayoutParams(params);
		DisplayMetrics dm = getResources().getDisplayMetrics();
		final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);
		TextView v = new TextView(getActivity());
		params.setMargins(margin, margin, margin, margin);
		v.setLayoutParams(params);
		v.setLayoutParams(params);
		v.setGravity(Gravity.CENTER);
		v.setText("首页界面");
		v.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, dm));
		fl.addView(v);
		return fl;
		*/
		View view = inflater.inflate(R.layout.activity_list, container,false);
		list = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
		
		list.setAdapter(mAdapter);

		list.setOnRefreshListener(new OnRefreshListener2<ListView>()
				{
					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ListView> refreshView)
					{
						Log.e("TAG", "onPullDownToRefresh");
						//这里写下拉刷新的任务
						new GetDataTask().execute();
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ListView> refreshView)
					{
						Log.e("TAG", "onPullUpToRefresh");
						//这里写上拉加载更多的任务
						new GetDataTask().execute();
					}
				});

		
		
		return view;
	}
	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        initDatas();
//        b = savedInstanceState;  
        mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, mListItems);
        setListAdapter(mAdapter);
	}
	
	private List<? extends Map<String, ?>> getData(String[] strs) {  
        List<Map<String ,Object>> list = new ArrayList<Map<String,Object>>();  
          
        for (int i = 0; i < strs.length; i++) {  
            Map<String, Object> map = new HashMap<String, Object>();  
            map.put("title", strs[i]);  
            list.add(map);
        }  	
          
        return list;  
    }
	
	@Override  
    public void onListItemClick(ListView l, View v, int position, long id) {  
        super.onListItemClick(l, v, position, id);  
          
        System.out.println(l.getChildAt(position));  
        HashMap<String, Object> view= (HashMap<String, Object>) l.getItemAtPosition(position);  
        System.out.println(view.get("title").toString()+"+++++++++title");  
  
        Toast.makeText(getActivity(), TAG+l.getItemIdAtPosition(position), Toast.LENGTH_LONG).show();  
        System.out.println(v);  
          
        System.out.println(position);  
    }  
	
	private void initDatas()
	{
		// 初始化数据和数据源
		mListItems = new LinkedList<String>();

		for (int i = 0; i < 15; i++)
		{
			mListItems.add("" + i);
		}
	}
	
	private class GetDataTask extends AsyncTask<Void, Void, String>
	{

		@Override
		protected String doInBackground(Void... params)
		{
			try
			{
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
			}
			return "" + (mItemCount++);
		}

		@Override
		protected void onPostExecute(String result)
		{
			mListItems.add(result);
			mAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			list.onRefreshComplete();
		}
	}
	
}
