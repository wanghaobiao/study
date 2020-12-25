package com.study.basics.multiThread.lock;

import org.aspectj.weaver.ast.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockIIIII {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args)  {
        final LockIIIII lockIIIII = new LockIIIII();

        new Thread(){
            @Override
            public void run() {
                lockIIIII.get(Thread.currentThread());
            };
        }.start();

        new Thread(){
            @Override
            public void run() {
                lockIIIII.get(Thread.currentThread());
            };
        }.start();

    }

    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }
}
