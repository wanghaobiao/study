package com.study.basics.multiThread.threadPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * submit()和 execute()方法的区别
 */
public class threadPoolIIIII {

    //private static ExecutorService executorService = new ThreadPoolExecutor(30, 30,60L, TimeUnit.SECONDS,new PriorityBlockingQueue());
    static ExecutorService executorService = Executors.newFixedThreadPool(30);

    public static void submit() {
        try {
            Date startDate = new Date();
            List<Future<String>> futures = new ArrayList<>();
            for (int i = 1; i <= 10000; i++) {
                final int ind = i;
                Future<String> submit = executorService.submit(() -> {
                    System.out.println("线程名称:"+Thread.currentThread().getName()+";定位:"+ind);
                /*try {
                    Thread.sleep( ind * 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                    return "结果：" + ind;
                });
                futures.add(submit);
            }
            for (Future<String> future : futures) { try { future.get(); } catch (Exception e) { } }
            System.out.println("=================>共耗时"+(System.currentTimeMillis()  - startDate.getTime())+"毫秒");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void execute() {
        try {
            Date startDate = new Date();
            for (int i = 1; i <= 10000; i++) {
                final int ind = i;
                executorService.execute(() -> {
                    System.out.println("线程名称:"+Thread.currentThread().getName()+";定位:"+ind);
                /*try {
                    Thread.sleep( ind * 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                });
            }
            System.out.println("=================>共耗时"+(System.currentTimeMillis()  - startDate.getTime())+"毫秒");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        execute();
        //submit();
    }
}

