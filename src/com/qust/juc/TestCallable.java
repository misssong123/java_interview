package com.qust.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {
public static void main(String[] args) {
	Callabledemo t=new Callabledemo();
	FutureTask<Integer> result=new FutureTask<>(t);
	new Thread(result).start();
	try {
		Integer num=result.get();
		System.out.println(num);
		System.out.println("--------------------");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
class Callabledemo implements Callable<Integer>{
    int sum=0;
	@Override
	public Integer call() throws Exception {
		for(int i=0;i<20000000;i++)
			sum+=3;
		return sum;
	}
}