package com.qust.juc;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
//相关代码演示
public class TestCopyOnWriteArrayList {
public static void main(String[] args) {
	//ArrayList<Integer> arr=new ArrayList<>();
	final CopyOnWriteArrayList<Integer> arr=new CopyOnWriteArrayList<>();
	arr.add(1);
	arr.add(2);
	arr.add(3);
	arr.add(4);
	System.out.println(arr);
	for(int i=5;i<7;i++){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Iterator<Integer> iterator=arr.iterator();
				while(iterator.hasNext()){
					System.out.println(Thread.currentThread().getName()+":"+iterator.next());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					arr.add(5);
				}	
			System.out.println(Thread.currentThread().getName()+":"+arr);
			}
			
		},""+i).start();;
	}
	
}
}
