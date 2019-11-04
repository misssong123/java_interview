package com.qust;
class Data{
    public volatile int num;
    public void add(){
        num++;
    }
}
public class VolatileDemo {
    public static void main(String[] args) {
      //ableSee();
        unableAtomic();
    }
    //保证可见性
    public static void ableSee(){
        Data data=new Data();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("操作执行开始");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.add();
                System.out.println("操作执行结束");
            }
        },"线程1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (data.num==0){

                }
                System.out.println("我已经获取到了值");
            }
        },"线程2").start();
    }
    //不能保证原子性
    public static void unableAtomic(){
        Data data=new Data();
        for(int j=0;j<20;j++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int k=0;k<1000;k++){
                        data.add();
                    }
                }
            },"线程"+j).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(data.num);
    }

}
