package com.qust.juc;
//模拟CAS基本操作
public class TestCAS {
public static void main(String[] args) {
	final Data data=new Data();
	for(int i=0;i<10;i++){
		new Thread(new Runnable() {
			@Override
			public void run() {
				int expect=data.get();
				System.out.println(data.set(expect, 10));
			}
		}).start();
	}
}
}
class Data {
	private int num;
	public synchronized int  get(){
		return num;
	}
	public synchronized int compareAndSet(int expect,int newValue){
		int oldValue=num;
		if (expect==oldValue) {
			num=newValue;
		}
		return oldValue;
	}
	public synchronized boolean set(int expect,int newValue){
		return expect==compareAndSet(expect, newValue);
	}
}
