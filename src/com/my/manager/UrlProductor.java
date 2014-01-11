package com.my.manager;

import org.apache.log4j.Logger;

import com.my.filter.HtmlFilter;

public class UrlProductor implements Runnable {

	private Logger log=Logger.getLogger(UrlProductor.class);
	@Override
	public void run() {
		while(true){
		      HtmlFilter hf=new HtmlFilter(UrlManager.getWaitURLMap());
		      hf.filter();
		      
		      UrlManager.addURL(hf.getUseMap());
		      
		      try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	}
