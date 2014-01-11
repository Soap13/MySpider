package com.my;

import com.my.manager.UrlCustomer;
import com.my.manager.UrlManager;
import com.my.manager.UrlProductor;

public class UrlMain {

	
	public static void main(String[] args) {
		UrlManager.addURL("http://product.pcpop.com/Mobile/00000_1.html");
		
		UrlCustomer uc=new UrlCustomer();
		
		UrlProductor up=new UrlProductor();
		
		Thread c1=new Thread(uc,"c1");
		Thread c2=new Thread(uc,"c1");
		Thread c3=new Thread(uc,"c1");
		Thread c4=new Thread(uc,"c1");
		Thread c5=new Thread(uc,"c1");
		
		Thread p1=new Thread(up,"c1");
		Thread p2=new Thread(up,"c1");
		Thread p3=new Thread(up,"c1");
		Thread p4=new Thread(up,"c1");
		Thread p5=new Thread(up,"c1");
		
		
		c1.start();
		c2.start();
		
		p1.start();
		p2.start();
	}
}
