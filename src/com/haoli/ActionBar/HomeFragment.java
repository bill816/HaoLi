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
import com.haoli.data.NewsDataBase;

import android.content.Context;
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
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;

/**
 * 首页Fragment的界面
 * 
 * 
 * @author guoxiao
 */
public class HomeFragment extends ListFragment {
	 
	private PullToRefreshListView mListView;
	private Adapter mAdapter = new Adapter();
	private String TAG = HomeFragment.class.getName();
	public  ArrayList<NewsDataBase> mListData;
	private LayoutInflater mInflater;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_list, container,false);
		mInflater = inflater;
		return view;
	}
	
	 // 加载多次  
	 public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);  
        mListView = (PullToRefreshListView) getActivity().findViewById(R.id.pull_refresh_list);
        if(mListData != null && mListData.size() > 0) { 
        	return;
        }
        mListData = new ArrayList<NewsDataBase>();
        setListAdapter(mAdapter);
		mListView.onRefreshComplete(); 
		
		mListView.setOnRefreshListener(new OnRefreshListener2<ListView>()
		{
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView)
			{
				//这里写下拉刷新的任务
				new GetDataTask().execute(mListData.size());
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView)
			{
				//这里写上拉加载更多的任务
				new GetDataTask().execute(mListData.size());
			}
		});
			
	 }  

	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  

	}
	
	
	@Override  
    public void onListItemClick(ListView l, View v, int position, long id) {  
        super.onListItemClick(l, v, position, id);  
        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_LONG).show();
    } 
	
	/**  
	 * 生成该类的对象，并调用execute方法之后  
	 * 首先执行的是onProExecute方法  
	 * 其次执行doInBackgroup方法  
	 *  
	 */
	private class GetDataTask extends AsyncTask<Integer, Void, ArrayList<NewsDataBase>>
	{

		 /**  
	     * 这里的Integer参数对应AsyncTask中的第一个参数，代表第几页新闻，最新的新闻值为1   
	     * 这里的String返回值对应AsyncTask的第三个参数  
	     * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的空间进行设置和修改  
	     * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作  
	     */
		@Override
		protected ArrayList<NewsDataBase> doInBackground(Integer... params)
		{
			int page = params[0].intValue();
			
			ArrayList<NewsDataBase> dataList = new ArrayList<NewsDataBase>();
			NewsDataBase data = new NewsDataBase();
			data.setNewsTitle("test test test test test test test test");
			data.setNewsTime("20140801");
			data.setNewsSummary("sumary sumarysumary11111111111");
			dataList.add(data);
			
			try
			{
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
			}
			return dataList;
		}

		@Override
		protected void onPostExecute(ArrayList<NewsDataBase> result)
		{
			mListData.addAll(result);
			mAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			mListView.onRefreshComplete();
		}
	}
	
	
	 public final class ViewHolder {  
	        public TextView title;  
	        public TextView time;
	        public TextView sumary;
	    }
	 // 添加列表内容  
    public class Adapter extends BaseAdapter {  
        public Adapter(){}  
        @Override  
        public boolean areAllItemsEnabled() {  
            return super.areAllItemsEnabled();  
        }  
          
        public Adapter(Context context) {  
        }  
        @Override  
        public int getCount() {  
            return mListData.size();  
        }  
        @Override  
        public Object getItem(int position) {  
            return null;  
        }  
          
        @Override  
        public long getItemId(int position) {
            return position;  
        }  
  
        @Override  
        public View getView(final int position, View convertView,  
                ViewGroup parent) {  
            final NewsDataBase news = mListData.get(position);  
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.list_item, null);  
                holder.title = (TextView) convertView.findViewById(R.id.item_text_title);  
                holder.time = (TextView) convertView.findViewById(R.id.item_text_time);  
                holder.sumary = (TextView) convertView.findViewById(R.id.item_text_sumary);
                convertView.setTag(holder);
            } else {  
                holder = (ViewHolder) convertView.getTag();  
            }  
            // 进行数据设置  
            holder.title.setText(news.getNewsTitle());  
            holder.time.setText(news.getNewsTime()); 
            holder.sumary.setText(news.getNewsSummary());
            return convertView;  
        }  
    }  
}
