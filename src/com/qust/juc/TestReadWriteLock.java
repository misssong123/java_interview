package com.qust.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteDemo demo=new ReadWriteDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.setNum();
            }
        }).start();
        for (int i=0;i<20;i++)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.getNum();
                }
            }).start();

    }
}
class ReadWriteDemo{
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private int num=0;
    public void getNum(){
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+":"+num);
        }  finally {

            readWriteLock.readLock().unlock();
        }

    }
    public void setNum(){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName());
            num=(int)(Math.random()*101);
        }finally {

            readWriteLock.writeLock().unlock();
        }
    }
}
