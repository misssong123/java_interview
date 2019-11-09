package com.qust;
//测试八种不同情况
public class TestSynchronized {
    public static void main(String[] args) {
        Number number=new Number();
        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getOne();
            }
        },"One").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getTwo();
            }
        },"Two").start();
    }

}
class Number{
    public synchronized void getOne(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":One");
    }
    public static synchronized void getTwo(){
        System.out.println(Thread.currentThread().getName()+":Two");
    }
    public synchronized void getThree(){
        System.out.println(Thread.currentThread().getName()+":Three");
    }
}