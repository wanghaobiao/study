package com.study.basics.multiThread.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class poolExecutor {
    /**
     * 使用ThreadPoolExecutor线程池
     */
    public  static void poolExecutor(){
        // 创建一个线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,6,2, TimeUnit.SECONDS ,
                new ArrayBlockingQueue<Runnable>(1),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0;i<8;i++ ){
            MyThread myThread = new MyThread();
            threadPoolExecutor.execute(myThread);
        }
    }

    public static void main(String[] args) {
        poolExecutor();
    }

}
