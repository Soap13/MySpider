package com.my.test;

import java.util.HashSet;
import java.util.Set;

/**
 * 算是个URL的测试管理
 */
public class URLManage {

	/** URL存储*/
	public static Set<String> urlSet=new HashSet<String>();
	
	/**URL添加*/
	public static void addUrl(String url){
	   System.out.println("@@@添加："+url);
	   URLManage.urlSet.add(url);
	}
	
	/**URL移除*/
	public static void removeUrl(String url){
		System.out.println("@@@移除："+url);
		if(URLManage.urlSet.contains(url)){
			URLManage.urlSet.remove(url);
			System.err.println("###URL移除成功");
		}else{
			System.err.println("***URL移除失败");
		}
	}
	
	/**URL的个数*/
	public static void getTheSize(){
		System.out.println("***URL个数："+URLManage.urlSet.size());
	} 
}
