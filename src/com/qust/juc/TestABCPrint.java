package com.qust.juc;
//测试顺序打印ABC
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 开始三个线程，线程ID分别为ABC
 * 分别打印自己ID十次，每次按顺序打印
 *
 */
public class TestABCPrint {
    public static void main(String[] args) {
        LockDemo demo=new LockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    demo.loopA(i);
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    demo.loopC(i);
                }
            }
        },"C").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    demo.loopB(i);
                }
            }
        },"B").start();
    }
}
class LockDemo {
    private int num=1;//线程标记
    private Lock lock=new ReentrantLock();//实现控制
    private Condition condition1=lock.newCondition();//每一种condition代表一种线程通信
    private Condition condition2=lock.newCondition();
    private Condition condition3=lock.newCondition();
    public  void loopA(int count){
        lock.lock();
        try{
            if (num!=1)
                condition1.await();
                System.out.println(Thread.currentThread().getName()+":A---"+count);
                num=2;
                condition2.signal();

        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }
    public  void loopB(int count ){
        lock.lock();
        try{
            if (num!=2)
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":B---"+count);
                num=3;
                condition3.signal();

        }finally {
            lock.unlock();
        }
    }
    public  void loopC(int count){
        lock.lock();
        try{
            if (num!=3)
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":C---"+count);
                num=1;
                condition1.signal();

        }finally {
            lock.unlock();
        }
    }
}