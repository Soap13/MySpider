package com.my.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 这个作为URL的管理类
 * 对于数据的操作要锁定的
 */
public class UrlManager {

	private static Logger log = Logger.getLogger(UrlManager.class);

	/** 所有的URL管理 */
	private static Set<String> allURLSet = new HashSet<String>();

	/** 管理可以能够执行的URL */
	private static Map<String, Integer> useURLMap = new HashMap<String, Integer>();

	/** 管理等待处理的URL */
	private static Map<String, Integer> waitURLMap = new HashMap<String, Integer>();
   
	/** 用于对临时信息的锁*/
	private static Object objWait=new Object();

//--------------------------------------------------------------------------------
	/**
	 * 添加一个种子 单纯添加会按照0来处理的
	 * @param String URL
	 */
	public static  void addURL(String url) {
		 addURL(url,Integer.valueOf(0));
	}

	/**
	 * 添加一个种子 地址-深度
	 * @param String URL
	 * @param String deep
	 */
	public static  void addURL(String url, Integer deep) {
		   log.info("\n---将要添加了一个种子-深度：" + url + "-" + deep.toString());
			
			if (UrlManager.allURLSet.contains(url)) {
				log.info("\n---添加已经存在，直接返回：" + url);
				return;
			}
			
			UrlManager.useURLMap.put(url,deep);
			UrlManager.allURLSet.add(url);
			
			log.info("\n---添加完成---");
	}
	
	/** 
	 * 批量添加种子 
	 * 因为待处理是个map
	 * @param Map<String,Integer> urlMap
	 */
   public static void addURL(Map<String,Integer> urlMap){
	   
	   log.info("\n---批量添加开始---");
	   Iterator<String> iterator=urlMap.keySet().iterator();
	   while(iterator.hasNext()){
		   String k=(String) iterator.next();
		   Integer v=urlMap.get(k);
		   addURL(k,v);
	   }
	   log.info("\n---批量添加结束---");
   }
   
	/** 
	 * 得到一个种子存储是  k-v=url-deep
	 * 这里遍历随机性质的  反对存储中的第一个
	 */
   public static  Map<String,Integer> getOneUrl(){
	   log.info("\n---获取一个种子开始---");
	   Map<String,Integer>result=new HashMap<String,Integer>();
	   String k="";
	   Integer v=new Integer(0);
	   
	   if(useURLMap.size()>0){
		   k=useURLMap.keySet().iterator().next();
		   v=useURLMap.get(k);
		   useURLMap.remove(k);
		   log.info("\n---获取一个种子成功，并移除---"+k+"-"+v.toString());
	   }
	   log.info("\n---获取一个种子结束---");
	   result.put(k,v);
	   return result;
   }
//------------------------------------------------------------- 
   /**
    * 存放的临时地址信息
    * 放完一次取一次
    * @return Map<String,Integer>
    */
   public static  Map<String,Integer> getWaitURLMap(){
	   synchronized(objWait){   
	     log.info("\n---获得处理信息开始---");  
	     //赋值到临时
	     Map<String,Integer>result=waitURLMap;
	     //清空
	     waitURLMap.clear();
	     log.info("\n---获得处理信息结束，清空信息表---");
	     return result;
	   }
   }
   
   /**
    *  添加信息
    *  但是要保证添加的过程和获取的过程是一个互斥 
    *  批量添加控制
    */
   private static void addWaitURL(String url ,Integer deep){
	   log.info("\n---添加了一个等待处理的URL:"+url+"-"+deep);
       if(!waitURLMap.containsKey(url)){
    	   waitURLMap.put(url, deep);
       }   
	   log.info("\n---添加结束");
   }
   
   /**
    * 采取批量添加的形式 
    */
   public static void addWaitURL(Map<String,Integer>waitURLs){
	   //同步过滤处理
	   synchronized (objWait) {
		log.info("\n---批量添加待处理地址---");
		waitURLMap.putAll(waitURLs);
		log.info("\n---批量添加待处理地址结束---");
	}
   }
//-------------------------------------------------------------
   
//-------------------------------------------------------------   
}
