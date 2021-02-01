package com.study.basics.multiThread.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

/**
 * Runnable和Callable的区别
 * 相同点
 * 1、两者都是接口；（废话）
 * 2、两者都可用来编写多线程程序；
 * 3、两者都需要调用Thread.start()启动线程；
 * 不同点
 * 1、两者最大的不同点是：实现Callable接口的任务线程能返回执行结果；而实现Runnable接口的任务线程不能返回结果；
 * 2、Callable接口的call()方法允许抛出异常；而Runnable接口的run()方法的异常只能在内部消化，不能继续上抛；
 */
public class ThreadIIIII implements Callable<String> {

    public ThreadIIIII(String acceptStr) {
        this.acceptStr = acceptStr;
    }

    private String acceptStr;

    @Override
    public String call() throws Exception {
        // 任务阻塞 1 秒
        Thread.sleep(1000);
        return this.acceptStr + " 追加一些字符并返回!";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new ThreadIIIII("my callable test!");
        FutureTask<String> task = new FutureTask<>(callable);
        long beginTime = System.currentTimeMillis();
        // 创建线程
        new Thread(task).start();
        // 调用get()阻塞主线程，反之，线程不会阻塞
        String result = task.get();
        long endTime = System.currentTimeMillis();
        System.out.println("hello : " + result);
        System.out.println("cast : " + (endTime - beginTime) / 1000 + " 秒!");
    }
}
