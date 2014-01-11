package com.my.test;

import java.util.concurrent.Semaphore;

public class Signs {

	//假设有十个生产者
	public static Semaphore product=new Semaphore(1);
	//两个消费者
    public static Semaphore customer=new Semaphore(1);
    //
    public static Semaphore mutex1=new Semaphore(1);  
    public static Semaphore mutex2=new Semaphore(1);  
}
