package com.qust.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//测试锁的使用
public class TestLocks {
public static void main(String[] args) {
	Ticket ticket=new Ticket();
	new Thread(ticket,"窗口一").start();
	new Thread(ticket,"窗口二").start();
	new Thread(ticket,"窗口三").start();
}
}
class Ticket implements Runnable{
	int num=100;
	Lock lock=new ReentrantLock();
	@Override
	public void run() {
		while(true){
			lock.lock();
			try {
				if (num>0) {
					System.out.println(Thread.currentThread().getName()+":"+ num--);
				}else{break;}
				
			} finally {
				lock.unlock();
			}
		}
	}
}