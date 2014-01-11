package com.my.test;

import com.my.manager.UrlManager;
import com.my.test.Signs;
import com.my.test.URLManage;

public class CustomerURL implements Runnable {

	private static int index=2;
	@Override
	public void run() {
	while(true){	
		//首先判断执行的数量
		try {   
	       //Signs.customer.acquire();
			System.out.println("---获取种子："+Thread.currentThread().getName()+"---正在执行");
            System.out.println("---获取："+UrlManager.getOneUrl());
            
			//Signs.mutex1.acquire();
			//URLManage.addUrl(String.valueOf(index++));
			//URLManage.getTheSize();
			// UrlManager.addURL(String.valueOf(index++));
			//Signs.mutex1.release();
			
			//Signs.customer.release();
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("---消费失败---");
			e.printStackTrace();
		}
	}
	}
}
