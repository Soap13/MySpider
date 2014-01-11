package com.my.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.my.manager.UrlManager;

/**
 * 这里声明一些常用的方法
 * 1.检测字符串为NULL，或者"" 
 * 2.返回真实的字符串NULL="" 
 * 3.检测一个路径是否是文件 
 * 4.检测一个文件夹是否存在  
 */
public class MyUtil {

	/**
	 * 1.检测字符串为NULL，或者"" 
	 */
   public static boolean isRealString(String arg){
	   if(arg==null||arg.trim().equals("")){
		   return false;
	   }else{
		   return true;
	   }
   }
   
   /**
    * 2.返回真实的字符串NULL="" 
    */
   public static String getRealString(String arg){
	   return arg==null?"":arg;
   }
   
   /**
    * 3.检测一个路径是否是文件 
    */
   public static boolean isFilePath(String path){
	   if(!isRealString(path)){
		   return false;
	   }
	   
	   File file=new File(path);
	   
	   return file.isFile()?true:false;
   }
   /**
    * 4.检测一个文件夹是否存在 
    */
   public static boolean isFolderPath(String path){
	   if(!isRealString(path)){
		   return false;
	   }
	   
       File file=new File(path);
	   
       return file.isDirectory()?true:false;
   }
   
   /**
    * 5.set转换为map  
    */
   public static Map<String,Integer>changeCollection(Set<String> urlSet,Integer deep){
	   Map<String,Integer>urlMap=new HashMap<String, Integer>();
	   Iterator<String> ite=urlSet.iterator();
	   while(ite.hasNext()){
		   String k=ite.next();
		   urlMap.put(k, deep);
	   }
	   return urlMap;
   }
}