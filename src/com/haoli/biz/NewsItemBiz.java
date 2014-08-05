package com.haoli.biz;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import com.haoli.bean.NewsDataBase;
import com.haoli.bean.NewsDataDetail;

//处理文章业务类
public class NewsItemBiz  
{  
	final static int BUFFER_SIZE = 4096; 
	private static Context mContext;
	
	public static List<NewsDataBase> getNewsDataBase(int newsType,int currpage,Context context){
		List<NewsDataBase> newsItems = new ArrayList<NewsDataBase>();
		NewsDataBase newsItem = null;
		mContext = context;
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
			
			/*********************get title*******************/
			Element a_ele = dl_ele.getElementsByTag("a").get(0);
			String title = a_ele.textNodes().get(0).text();
			newsItem.setNewsTitle(title);

			/*********************get id*******************/
			String onclickString = a_ele.attr("onclick");
			String idStr = onclickString.replaceAll("^.*\\(", "").replaceAll("\\).*", "");
			newsItem.setNewsID(idStr);
			
			/*********************get time*******************/
			Element em_ele = dl_ele.getElementsByTag("em").get(0);
			String time = em_ele.text();
			newsItem.setNewsTime(time);
			
			/*********************get summary*******************/
			Element dd_ele = dl_ele.getElementsByTag("dd").get(0);
			String sumary = dd_ele.textNodes().get(0).text();
			newsItem.setNewsSummary(sumary);
			newsItems.add(newsItem);
			Log.d("[GX]","----------" + title);
		 }
		return newsItems;
	}
	
	public static String getNewsDetail(String id){
		//String jsonStr = "{"title":"每日必读 2014-8-5","author":"曾海龙","className":"操盘必读","addtime":"2014-8-5 9:18:01","content":"<font face='黑体'>（1）万丰奥威上半年净利2亿元 拟10股派5元<br>（2）金正大上半年净利4.79亿元 同比增长27%<br>（3）哈空调拟1.31亿底价售上海天勃100%股权<br>（4）江铃汽车7月汽车销量22031辆 同比增33%<br>（5）维尔利拟2590万元受让常州大维51%股权<br>（6）信质电机上半年净利8069万 拟10股转5股<br>（7）三星电气中期净利1.03亿 同比增22.88%<br>（8）复星医药投5亿元与公立医院合作<br>（9）长园集团利好频发 沃尔核材举牌浮盈约3000万<br>（10）明牌珠宝进军在线教育 前景不明</font>"}"
		String jsonStr=null;
		try {
			InputStream is = mContext.getAssets().open("test.json");
			jsonStr = inputStreamTOString(is);
//			Log.d("[GX]", htmlStr);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {  
            JsonReader reader=new JsonReader(new StringReader(jsonStr));  

            reader.beginObject();  
            String key=reader.nextName();  
            if("title".equals(key))  
            {  
                System.out.println(reader.nextString());
                key=reader.nextName();
            }
            if("author".equals(key))
            {  
                System.out.println(reader.nextString());
                key=reader.nextName();
            }  
            if("className".equals(key))
            {  
                System.out.println(reader.nextString()); 
                key=reader.nextName();
            } 
            if("addtime".equals(key))  
            {  
                System.out.println(reader.nextString());  
                key=reader.nextName();
            }
            if("content".equals(key))  
            {  
                System.out.println(reader.nextString());  
            }
            reader.endObject();  
              
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
		return null;
	}
	
	/**  
     * 将InputStream转换成String  
     * @author guoxiao
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