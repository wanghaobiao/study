package com.study.basics.multiThread.lock;

import org.aspectj.weaver.ast.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock里面提供了很多丰富的方法，不过最主要的有两个方法：
 * readLock()
 * writeLock()
 * 用来获取读锁和写锁。
 * LockIIII  与  LockIIIII比较看
 */
public class LockIIII {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args)  {
        final LockIIII lockIIII = new LockIIII();

        new Thread(){
            @Override
            public void run() {
                lockIIII.get(Thread.currentThread());
            };
        }.start();

        new Thread(){
            @Override
            public void run() {
                lockIIII.get(Thread.currentThread());
            };
        }.start();

    }

    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            try {
                thread.sleep( 500 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName()+"正在进行读操作");
        }
        System.out.println(thread.getName()+"读操作完毕");
    }
}
