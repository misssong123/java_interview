package com.qust.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//测试同一个Runnable在不同线程中的使用,和线程池的区别
public class TestRunnable {
    public static void main(String[] args) {
        Runnabledemo demo=new Runnabledemo();
       for(int i=0;i<5;i++)
            new Thread(demo,"线程"+i).start();
        ExecutorService es= Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++)
            es.submit(demo);
        es.shutdown();
    }
/**
 *线程3:9065
 * 线程2:9065
 * 线程1:9065
 * 线程0:9065
 * pool-1-thread-1:9065
 * pool-1-thread-5:9065
 * pool-1-thread-4:9065
 * 线程4:9065
 * pool-1-thread-3:9065
 * pool-1-thread-2:9065
 * */
}

class Runnabledemo implements Runnable{
    int i=0;
    @Override
    public void run() {
        while(i<=100) {
            i++;
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
