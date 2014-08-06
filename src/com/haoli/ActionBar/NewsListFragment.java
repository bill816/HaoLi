package com.haoli.ActionBar;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.haoli.R;
import com.haoli.activity.NewsDetail;
import com.haoli.bean.NewsDataBase;
import com.haoli.biz.NewsItemBiz;
import com.haoli.net.HaoliRestClient;
import com.haoli.utils.URLUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 首页Fragment的界面
 * 
 * 
 * @author guoxiao
 */
public class NewsListFragment extends ListFragment {
	 
	private PullToRefreshListView mListView;
	private Adapter mAdapter = new Adapter();
	private String TAG = NewsListFragment.class.getName();
	public  ArrayList<NewsDataBase> mListData;
	private LayoutInflater mInflater;
	private int mCurrPage = 1;
	private int mGetpage = 1;
	protected String mRelativeUrl;
	private String mNewsType;
	
	public NewsListFragment(String relativeurl,String newsType){
		mRelativeUrl = relativeurl ;
		mNewsType = newsType;
	}
	public NewsListFragment(){
		
	}
	
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
        mGetpage = mCurrPage;
        HaoliRestClient.get(mRelativeUrl+"?r=6&page=" + mCurrPage++, null, resphandler);
		mListView.onRefreshComplete();
		
		mListView.setOnRefreshListener(new OnRefreshListener2<ListView>()
		{
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView)
			{
				//这里写下拉刷新的任务
				mGetpage = 1;
				HaoliRestClient.get(mRelativeUrl+"?r=6&page=1", null, resphandler);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView)
			{
				//这里写上拉加载更多的任务
				///module/shichangyanjiu/caopangbidu.asp?r=6&page=2
				mGetpage = mCurrPage;
				HaoliRestClient.get(mRelativeUrl+"?r=6&page=" + mCurrPage++, null, resphandler);
			}
		});
		mListView.refreshDrawableState();
	 }  

	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
	}

	@Override  
    public void onListItemClick(ListView l, View v, int position, long id) {  
        super.onListItemClick(l, v, position, id);  

        Intent intent = new Intent(getActivity(), NewsDetail.class);
        Bundle bundleSimple = new Bundle();
        bundleSimple.putString("id", mListData.get(position).getNewsID());
        intent.putExtras(bundleSimple);
		startActivity(intent);
//        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_LONG).show();
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
    
    AsyncHttpResponseHandler resphandler = new AsyncHttpResponseHandler(){
		@Override
        public void onSuccess(String response) {
//            System.out.println(response);
			
			List<NewsDataBase> result = NewsItemBiz.htmlStrToNewsDataBase(0, response);
			//如果有新的数据则清掉原来的数据
			//如果加载页数为第1页，并且缓存里数据不为空时，对比第一个是否相等来判断是否有数据更新
			if(mListData.isEmpty()){
				mListData.addAll(result);
			}else if(1 == mGetpage
					&& !mListData.get(0).getNewsID().equals(result.get(0).getNewsID())){
				mListData.clear();
				mListData.addAll(result);
			}else if(1 != mGetpage){
				mListData.addAll(result);
			}
			mAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			mListView.onRefreshComplete();
        }
		@Override
        public void onFailure(Throwable e, String response) {
            System.out.println(response);
        }
		
		@Override
        public void onFinish() {
			// Completed the request (either success or failure)
			
        }
	};
}
