package com.haoli.bean;

/**
 * 新闻数据基类
 * 
 * @author guoxiao
 */

public class NewsDataDetail extends NewsDataBase {

	/*********内容，作者，类型，权限****/
	private String newsContent;
	private String newsAuther;
	private String newsType;
	private String newsPermission;
	
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	
	public String getNewsAuther() {
		return newsAuther;
	}
	public void setNewsAuther(String newsAuther) {
		this.newsAuther = newsAuther;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getNewsPermission() {
		return newsPermission;
	}
	public void setNewsPermission(String newsPermission) {
		this.newsPermission = newsPermission;
	}
}
