package com.qust;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
//代码演示
public class TestFork {
    public static void main(String[] args) {
        ForkJoinPool pool=new ForkJoinPool();
        ForkDemo forkDemo=new ForkDemo(0L,1000000L);
        long sum =pool.invoke(forkDemo);
        System.out.println(sum);
    }
}
class ForkDemo extends RecursiveTask<Long> {//RecursiveAction 实现方法无返回结果  RecursiveTask<Integer>实现方法有返回结果

    private long start;
    private long end;
    private final static  long TEMP=1000;//临界值

    public ForkDemo(long start,long end){
        this.start=start;
        this.end=end;
    }
    @Override
    protected Long compute() {
        long length=end-start;
        long sum=0;
        if (length<=TEMP){
           for(long i=start;i<=end;i++)
               sum+=i;
            return sum;
        }else {
            long mid=(start+end)/2;
         ForkDemo left=new ForkDemo(start,mid);
         left.fork();
         ForkDemo right=new ForkDemo(mid+1,end);
         right.fork();
         return left.join()+right.join();
        }
    }

}
