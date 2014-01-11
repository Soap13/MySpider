package com.my;

import com.my.manager.UrlManager;
import com.my.parse.HtmlParse;
import com.my.request.Request;

public class TT {

	public static void main(String[] args) {
//		UrlManager.addURL("http://www.oschina.net/");
		
		Request r=new Request("http://www.oschina.net");
		
		HtmlParse hp=new HtmlParse(r.getHttpInputStream(), new Integer(0));
		hp.getCurrentUrl();
	}
}
