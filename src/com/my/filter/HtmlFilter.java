package com.my.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.my.InitProperties;

/**
 * 地址过滤 
 */
public class HtmlFilter {
	
	private Logger log=Logger.getLogger(HtmlFilter.class);
 
	/** 要过滤的地址*/
	private Map<String,Integer> allURLMap;
	
	/** 无效的地址*/
	private Map<String,Integer> unUseURLMap;
	
	/** 转换的地址*/
	private Map<String,Integer> changeURLMap;
	
	/**过滤前缀*/
	private String preURL;
	
	/**深度*/
	private Integer deep;
	
	/**
	 * 构造方法要求得到 
	 */
	public HtmlFilter(Map<String,Integer> map){
		log.info("\n---过滤构造方法---");
		this.allURLMap=map;
		unUseURLMap=new HashMap<String,Integer>();
		changeURLMap=new HashMap<String,Integer>();
		initFilter();
		log.info("\n---过滤构造结束---");
	}
	
	
	/**实例化过滤的前缀和深度**/
	private void initFilter(){
		log.info("\n---得到配置修改内容---");
		Properties pro=InitProperties.getPropertiew("");
		preURL=pro.getProperty("preURL");
		deep=Integer.valueOf(pro.getProperty("deep"));
		log.info("\n---得到配置修改内容结束---");
	}
	
	/**
	 * 过滤
	 */
	public void filter(){
		//开始过滤
		doSimpleFilter();
		//移除
		delFilter();
		//添加
		addFilter();
	}
	
	/**基本过滤*/
	public void doSimpleFilter(){
		log.info("\n---过滤内容包括错误和前缀---");
		
		Set<String>urlSet=allURLMap.keySet();
	    Iterator<String> ite=urlSet.iterator();
	    while(ite.hasNext()){
	    	String k=ite.next();
	    	Integer v=allURLMap.get(k);
	    	//深度太大
	    	if(v.intValue()>deep.intValue())
	    	{ 
	    		log.info("\n---深度超标了剔除---");
	    		unUseURLMap.put(k, v);
	    		continue;
	    	}
	    	
	    	//规格不合适
	    	if(k.indexOf("/")<0){
	    		log.info("\n---过滤掉js的");
	    		unUseURLMap.put(k, v);
	    		continue;
	    	}
	    	
	    	//String pre=InitProperties.getPropertiew("").getProperty("");
	    	//范围过滤掉
//	    	if(k.indexOf(ch))
	    	//修改连接
	    	if(!(k.indexOf("http:")>0||k.indexOf("https:")>0)){
	    		log.info("\n---地址修正---");
	    		unUseURLMap.put(k, v);
	    		k+=preURL+"/"+k;
	    		changeURLMap.put(k, v);
	    		continue;
	    	}else if(k.indexOf(preURL)<0){
	    		log.info("\n---过滤范围的");
	    		unUseURLMap.put(k, v);
	    		continue;
	    	}
	    	
	    }
	}
	
	private void delFilter(){
		log.info("\n---删除无效的开始---");
		Iterator<String> ite=unUseURLMap.keySet().iterator();
		while(ite.hasNext()){
			String k=ite.next();
			allURLMap.remove(k);
		}
		
		unUseURLMap.clear();
		log.info("\n---删除无效的结束---");
	}
	private void addFilter(){
		log.info("\n---添加修改的开始---");
		Iterator<String> ite=changeURLMap.keySet().iterator();
		while(ite.hasNext()){
			String k=ite.next();
			Integer v=changeURLMap.get(k);
			allURLMap.put(k, v);
		}
		
		changeURLMap.clear();
		log.info("\n---添加修改的结束---");
	}
	
	public Map<String,Integer>getUseMap(){
		log.info("\n---返回可以用的地址---");
		return allURLMap;
	}
}
