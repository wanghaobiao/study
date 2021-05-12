package com.study.basics.multiThread.threadPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * submit()和 execute()方法的区别
 */
public class threadPoolIIIII {
    public static void execute() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            final int index = i;
            executorService.execute(() -> System.out.println(Thread.currentThread().getName() + "-" + index));
        }
        executorService.shutdown();
    }

    public static void submit() {
        List<Future<String>> res = new ArrayList<>();
        Date startDate = new Date();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int ind = i;
            Future<String> submit = executorService.submit(() -> {
                try {
                    Thread.sleep( ind * 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ind + " --> return String....");
                return "结果：" + ind;
            });
            res.add(submit);
        }

        res.forEach(r -> {
            try {
                System.out.println("返回值->" + r.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("=================>共耗时"+(System.currentTimeMillis()  - startDate.getTime())+"毫秒");
    }


    public static void main(String[] args) {
        //execute();
        submit();
    }
}

