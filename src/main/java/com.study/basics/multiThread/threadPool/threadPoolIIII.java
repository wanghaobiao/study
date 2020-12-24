package com.study.basics.multiThread.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newSingleThreadExecutor
 * 创建一个使用单个 worker 线程的 Executor，
 * 以无界队列方式来运行该线程。
 * （注意，如果因为在关闭前的执行期间出现失败而终止了此单个线程，那么如果需要，一个新线程将代替它执行后续的任务）。
 * 可保证顺序地执行各个任务，并且在任意给定的时间不会有多个线程是活动的。
 * 与其他等效的 newFixedThreadPool(1)不同，可保证无需重新配置此方法所返回的执行程序即可使用其他的线程。
 */
public class threadPoolIIII {
    public static void main(String[] args) {
        //单线程的线程池，线程异常结束，会创建一个新的线程，能确保任务按提交顺序执行
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

        //测试单线程的线程池
        for (int i = 1; i <= 5; i++) {
            int index = i;
            singleThreadPool.execute( new Runnable() {
                @Override
                public void run() {
                    try {
                        if(index == 3){
                            Object o = null;
                            o.toString();
                        }
                        System.out.println( "第" + index + "个线程" );
                        Thread.sleep( 2000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } );
        }
    }
}
