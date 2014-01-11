package com.my.manager;

import java.util.Map;

import org.apache.log4j.Logger;

import com.my.parse.HtmlParse;
import com.my.request.Request;
import com.my.util.MyUtil;

/**
 * 获取URL的顾客消费
 */
public class UrlCustomer implements Runnable {
    private Logger log=Logger.getLogger(UrlCustomer.class);
    
	@Override
	public void run() {
		while(true){
			Map<String,Integer> map=UrlManager.getOneUrl();
			
		  if(map!=null){	
	    	Request r=new Request(map);
	    	
	    	if(r.getHttpInputStream()!=null){
	    	  HtmlParse hp=new HtmlParse(r.getHttpInputStream(), r.getDeep());
	    		//HtmlParse hp=new HtmlParse(r.getUrl());
	          hp.getContent();
	    	  UrlManager.addWaitURL(MyUtil.changeCollection(hp.getCurrentUrl(), hp.getDeep()));
	    	}
	    	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		}
	}
}
