package com.qust.juc;

public class TestProductorAndConsumer {
public static void main(String[] args) {
	Clerk1 clerk=new Clerk1();
	new Thread(new Productor1(clerk),"生产者1").start();
	new Thread(new Consumer1(clerk),"消费者1").start();
	new Thread(new Productor1(clerk),"生产者2").start();
	new Thread(new Consumer1(clerk),"消费者2").start();
}
}
class Clerk1{
	private int product=0;
	//进货
	public synchronized void put(){
		while (product>=1) {
			System.out.println("产品已满");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			System.err.println(Thread.currentThread().getName()+":"+ ++product);
			this.notifyAll();
		
	}
	//卖货
	public synchronized void get(){
		while (product<=0) {
			System.out.println("产品缺货");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			System.err.println(Thread.currentThread().getName()+":"+ --product);
			this.notifyAll();
		
	}
}
class Productor1 implements Runnable{
	private Clerk1 clerk;
	public Productor1(Clerk1 clerk) {
		this.clerk=clerk;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			clerk.put();
		}
	}
}
class Consumer1 implements Runnable{
	private Clerk1 clerk;
	public Consumer1(Clerk1 clerk) {
		this.clerk=clerk;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			clerk.get();
		}		
	}	
}