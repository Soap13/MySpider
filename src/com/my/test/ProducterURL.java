package com.my.test;

import com.my.manager.UrlManager;
import com.my.test.Signs;

public class ProducterURL implements Runnable {

	private static int index=2;
	@Override
	public void run() {
    while(true){		
		try {
			//Signs.product.acquire();
			System.out.println("===生产者："+Thread.currentThread().getName()+"生产了产品");
			
			Signs.mutex1.acquire();
//			
//			URLManage.removeUrl(String.valueOf((index++)+1));
//			URLManage.getTheSize();
			UrlManager.addURL(String.valueOf((index++)+1));
			Signs.mutex1.release();
			
			//Signs.product.release();
			
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("---生产失败---");
			e.printStackTrace();
		}
		
	}
	}

}
