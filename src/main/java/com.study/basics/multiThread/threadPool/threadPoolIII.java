package com.study.basics.multiThread.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * newScheduledThreadPool  延迟执行
 * 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
 */
public class threadPoolIII {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool= Executors.newScheduledThreadPool(3);
        //延迟三秒执行
        scheduledThreadPool.schedule(new Runnable(){
            @Override
            public void run(){
                System.out.println("延迟三秒"+ Thread.currentThread().getName() );
            }
        }, 3, TimeUnit.SECONDS);
        //延迟1秒后每三秒执行一次
        scheduledThreadPool.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run() {
                System.out.println("延迟1秒后每三秒执行一次" + Thread.currentThread().getName() );
            }
        },1,3,TimeUnit.SECONDS);
    }
}
