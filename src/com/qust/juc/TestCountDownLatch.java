package com.qust.juc;



import java.util.concurrent.CountDownLatch;

//闭锁操作相关演示
public class TestCountDownLatch {

public static void main(String[] args)  {
	final CountDownLatch c=new CountDownLatch(5);
	long start=System.currentTimeMillis();
	for(int i=0;i<5;i++){
		new Thread(new LatchDown(c)).start();
	}
	try {
		c.await();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	long end=System.currentTimeMillis();
	System.out.println("完成花费的时间:"+(end-start));
}
}
class LatchDown implements Runnable{
	CountDownLatch c;
	public LatchDown(CountDownLatch countDownLatch) {
		// TODO Auto-generated constructor stub
		c=countDownLatch;
	}
	@Override
	public void run() {
		for(int i=0;i<10000;i++){
			if (i%200==0) {
				System.out.println(Thread.currentThread().getName()+":"+i);
			}
		}
		c.countDown();
	}
	
}
