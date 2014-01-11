package com.my;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 初始化的动作
 */
public class InitProperties {

	private static Logger log=Logger.getLogger(InitProperties.class);
	
	/**
	 * 保存每个包下面的配置文件信息 
	 */
	private static Map<String,Properties> propertiesMap	=new HashMap<String,Properties>();

	/**
	 * 得到当前运行的根目录 
	 */
	private static String nowPath=System.getProperty("user.dir");

	/**
	 * 文件名字
	 */
	private static String preName="myres.properties";

	/** 私有构造方法 */
	private InitProperties(){
	}	

	/**
	 * 通过包名找到当前的配置文件 
	 * @param packageName
	 * @return
	 */
	 
	public static Properties getPropertiew(String packageName){
	 log.info("\n---获取配置文件开始---");
		//1.简单检测下传入的参数
		String proPath=InitProperties.nowPath+
                       File.separator+
                       "bin"+
                       File.separator+
                       preName;
		
        //如果报名可以寻找得到		
		if(packageName!=null&&!packageName.equals("")){
		String packgePath=packageName.replace(".", File.separator);
		       proPath+=
		                 packgePath+
		                 File.separator+
		                 InitProperties.preName;
		}
		
		//3.添加文件
		Properties pro=propertiesMap.get(proPath);
		if(pro==null){
		   pro=new Properties();
		   try {
			pro.load(new FileInputStream(proPath));
		    propertiesMap.put(packageName, pro);
		} catch (FileNotFoundException e) {
			log.info("\n---获取配置失败---");
			pro=null;
			e.printStackTrace();
		} catch (IOException e) {
			log.info("\n---获取配置失败---");
			pro=null;
			e.printStackTrace();
		}
		}
		log.info("\n---获取配置文件结束---");
		return pro; 
	}

}
