package com.my.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.my.InitProperties;

/**
 * 构造请求头得到输入流
 */
public class Request {

	private Logger log = Logger.getLogger(Request.class);

	/** 请求实例 */
	private HttpURLConnection httpURLCon;
	private String url;
	private Integer deep;

	/** 构造方法 */
	public Request(String url) {
		this(url, new Integer(0));
	}

	/** 构造方法 */
	public Request(String url, Integer deep) {
		log.info("\n---请求实例化中：" + url + "-" + deep);
		this.url = url;
		this.deep = deep;
		initHttp();
		log.info("\n---实例化结束---");
	}

	/** 构造方法 */
	public Request(Map<String, Integer> map) {
		log.info("\n---请求实例化中：");
		if (map != null) {
			this.url = map.keySet().iterator().next();
			this.deep = map.get(url);
			initHttp();
		}
		log.info("\n---实例化结束---");
	}

	/** 实例化请求的给 */
	public void initHttp() {
		log.info("\n---http头文件构造开始---");

		try {
			URL urlCon = new URL(this.url);
			httpURLCon = (HttpURLConnection) urlCon.openConnection();
			
			Properties pro=InitProperties.getPropertiew("");
			httpURLCon.setRequestMethod(pro.getProperty("Method"));
			httpURLCon.setRequestProperty("User-Agent",pro.getProperty("User-Agent"));
			httpURLCon.setRequestProperty("Accept",pro.getProperty("Accept"));
			httpURLCon.setRequestProperty("Accept-Language",pro.getProperty("Accept-Language"));
			httpURLCon.setRequestProperty("Accept-Encodingt",pro.getProperty("Accept-Encoding"));
		} catch (IOException e) {
			log.error("\n--http头构造失败---"+httpURLCon.getURL());
			//httpURLCon.disconnect();
			e.printStackTrace();
		}
		log.info("\n---http头文件构造结束---");
	}

	/** 得到文地址 */
	public String getUrl() {
		return url;
	}

	/** 得到当前的深度 */
	public Integer getDeep() {
		return deep;
	}
    
	/** 返回一个输入流*/
	public InputStream getHttpInputStream(){
		log.info("\n---获得页面输入流--");
		if(httpURLCon!=null){
			try {
				return httpURLCon.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
