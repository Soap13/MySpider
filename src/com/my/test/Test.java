package com.my.test;

import com.my.manager.UrlManager;

public class Test {

	public static void main(String[] args) {
		
		UrlManager.addURL("1");
		
		CustomerURL c=new CustomerURL();
		ProducterURL p=new ProducterURL();
		int i=1;
		while(i>0){
		
			i--;
		Thread c1=new Thread(c,"1");
		Thread c2=new Thread(c,"2");
		Thread c3=new Thread(c,"3");
		Thread c4=new Thread(c,"4");
		Thread c5=new Thread(c,"5");
		
		
		Thread p1=new Thread(p,"1");
		Thread p2=new Thread(p,"2");
		Thread p3=new Thread(p,"3");
		Thread p4=new Thread(p,"4");
		Thread p5=new Thread(p,"5");
		
		 c1.start();
		 c2.start();
		 c3.start();
		 c4.start();
		 c5.start();

		 p1.start();
		 p2.start();
		 p3.start();
		 p4.start();
		 p5.start();

		 try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		 while(true);
		}
	}
}
