package com.haoli.biz;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import android.content.Context;
import android.util.Log;

import com.haoli.bean.NewsDataBase;

//处理文章业务类
public class NewsItemBiz  
{  
	final static int BUFFER_SIZE = 4096;  
	
	public static List<NewsDataBase> getNewsDataBase(int newsType,int currpage,Context context){
		List<NewsDataBase> newsItems = new ArrayList<NewsDataBase>();
		NewsDataBase newsItem = null;
		String htmlStr=null;
		try {
			InputStream is = context.getAssets().open("caopenbidu.html");
			
			htmlStr = inputStreamTOString(is);
//			Log.d("[GX]", htmlStr);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Document doc = Jsoup.parse(htmlStr);
		Elements units = doc.getElementsByClass("art");
		for (int i = 0; i < units.size(); i++) {
			newsItem = new NewsDataBase(); 
			Element unit_ele = units.get(i);
			Element dl_ele = unit_ele.getElementsByTag("dl").get(0);
			Element a_ele = dl_ele.getElementsByTag("a").get(0);
			String title = a_ele.textNodes().get(0).text();
			newsItem.setNewsTitle(title);
			
			
			Element em_ele = dl_ele.getElementsByTag("em").get(0);
			String time = em_ele.text();
			newsItem.setNewsTime(time);
			
			Element dd_ele = dl_ele.getElementsByTag("dd").get(0);
			String sumary = dd_ele.textNodes().get(0).text();
			newsItem.setNewsSummary(sumary);
			newsItems.add(newsItem);
			Log.d("[GX]","----------" + title);
		 }
		return newsItems;
	}
	
	/**  
     * 将InputStream转换成String  
     * @param in InputStream  
     * @return String  
     * @throws Exception  
     *   
     */  
    public static String inputStreamTOString(InputStream in) throws Exception{  
          
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[BUFFER_SIZE];  
        int count = -1;  
        while((count = in.read(data,0,BUFFER_SIZE)) != -1)  
            outStream.write(data, 0, count);  
          
        data = null;  
        return new String(outStream.toByteArray(),"UTF-8");
    }  
} 