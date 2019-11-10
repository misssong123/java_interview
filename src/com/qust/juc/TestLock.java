package com.qust.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//通过lock和condition实现生产者和消费者代码
public class TestLock {
	public static void main(String[] args) {
		Clerk clerk=new Clerk();
		new Thread(new Productor(clerk),"生产者1").start();
		new Thread(new Consumer(clerk),"消费者1").start();
		new Thread(new Productor(clerk),"生产者2").start();
		new Thread(new Consumer(clerk),"消费者2").start();
	}
	}
	class Clerk{
		private int product=0;
		private Lock lock=new ReentrantLock();
		private Condition condition =lock.newCondition();
		//进货
		public  void put(){
			lock.lock();
			try {
				while (product>=10) {
					System.out.println("产品已满");
					try {
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName()+":"+ ++product);
				condition.signalAll();
			} finally {
				lock.unlock();
			}
		}
		//卖货
		public  void get(){
			lock.lock();
			try {
				while (product<=0) {
					System.out.println("产品缺货");
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
					System.out.println(Thread.currentThread().getName()+":"+ --product);
					condition.signalAll();
			} finally {
				// TODO: handle finally clause
				lock.unlock();
			}
		}
	}
	class Productor implements Runnable{
		private Clerk clerk;
		public Productor(Clerk clerk) {
			this.clerk=clerk;
		}
		@Override
		public void run() {
			for(int i=0;i<20;i++){
				clerk.put();
			}
		}
	}
	class Consumer implements Runnable{
		private Clerk clerk;
		public Consumer(Clerk clerk) {
			this.clerk=clerk;
		}
		@Override
		public void run() {
			for(int i=0;i<20;i++){
				clerk.get();
			}		
		}	
	}
