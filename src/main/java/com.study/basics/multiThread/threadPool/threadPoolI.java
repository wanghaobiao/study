package com.study.basics.multiThread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newCachedThreadPool
 * 创建一个可根据需要创建新线程的线程池，
 * 但是在以前构造的线程可用时将重用它们。
 * 对于执行很多短期异步任务的程序而言，
 * 这些线程池通常可提高程序性能。
 * 调用 execute 将重用以前构造的线程（如果线程可用）。
 * 如果现有线程没有可用的，则创建一个新线程并添加到池中。
 * 终止并从缓存中移除那些已有 60 秒钟未被使用的线程。因此，长时间保持空闲的线程池不会使用任何资源。
 */
public class threadPoolI {
    public static void main(String[] args) {

        //可缓存的线程池，如果线程池的容量超过了任务数，自动回收空闲线程
        ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
        //ExecutorService cacheThreadPool =  Executors.newFixedThreadPool( 4 );

        //通过线程池创建
        for (int i = 1; i <= 3; i++) {
            final int index = i;
            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cacheThreadPool.execute( new Runnable() {
                @Override
                public void run() {
                    System.out.println( "线程池创建:第" + index + "个线程" + Thread.currentThread().getName() );
                }
            } );
        }
        //通过非线程池创建
        for (int i = 1; i <= 3; i++) {
            final int index = i;
            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread t1 = new Thread( () -> {
                System.out.println( "非线程池创建:第" + index + "个线程" + Thread.currentThread().getName() );
            });
            t1.start();
        }
    }
}
