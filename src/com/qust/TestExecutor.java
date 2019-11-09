package com.qust;

import jdk.management.resource.internal.inst.InitInstrumentation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//测试线程池的使用
public class TestExecutor {
    public static void main(String[] args) {
/*        ExecutorService es= Executors.newFixedThreadPool(5);
        List<Future> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            Future<Integer>  future=es.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    int sum=0;
                    for(int i=1;i<101;i++)
                        sum+=i;
                    return sum;
                }
            });
            list.add(future);
        }
        for(Future future:list) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        es.shutdown();
        */
        ScheduledExecutorService es=Executors.newScheduledThreadPool(5);
        for (int i=0;i<5;i++){
            Future<Integer> future=es.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum=0;
                    for(int i=1;i<101;i++)
                        sum+=i;
                    return sum;
                }
            }, 1, TimeUnit.SECONDS);
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        es.shutdown();
/*        Executordemo demo=new Executordemo();
        for(int i=0;i<5;i++){
            es.submit(demo);
        }
        es.shutdown();*/
    }
}
class Executordemo implements Runnable{
    int i=0;
    @Override
    public void run() {
        while(i<=100) {
            i++;
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}