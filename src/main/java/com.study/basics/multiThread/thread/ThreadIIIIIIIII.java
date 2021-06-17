package com.study.basics.multiThread.thread;

import io.swagger.annotations.ApiOperation;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic  用于保证原子性
 */
public class ThreadIIIIIIIII {
    static AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        new Thread(){
            @Override
            public void run() {
                for (int m = 0; m < 1000000; m++) {
                    ai.getAndIncrement();
                }
            };
        }.start();
        new Thread(){
            @Override
            public void run() {
                for (int m = 0; m < 1000000; m++) {
                    ai.getAndIncrement();
                }
            };
        }.start();
        Thread.sleep(500);
        System.out.println(ThreadIIIIIIIII.ai.get());
    }
}
