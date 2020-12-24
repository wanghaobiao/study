package com.study.basics.multiThread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newFixedThreadPool
 * 创建一个指定工作线程数量的线程池。
 * 每当提交一个任务就创建一个工作线程，
 * 如果工作线程数量达到线程池初始的最大数，
 * 则将提交的任务存入到池队列中等正在执行的线程执行完之后执行。
 */
public class threadPoolII {
    public static void main(String[] args) {

        //定长线程池，每当提交一个任务就创建一个线程，直到达到线程池的最大数量，这时线程数量不再变化
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool( 4 );

        //测试定长线程池，线程池的容量为3，提交5个任务，根据打印结果可以看出先执行前3个任务，3个任务结束后再执行后面的任务
        for (int i = 1; i <= 5; i++) {
            final int index = i;
            fixedThreadPool.execute( new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println( "第" + index + "个线程" + Thread.currentThread().getName() );
                        Thread.sleep( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } );
        }
    }
}
