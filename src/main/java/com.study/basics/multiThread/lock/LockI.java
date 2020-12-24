package com.study.basics.multiThread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ReentrantLock
 * 意思是“可重入锁”
 * 关于可重入锁的概念在下一节讲述。
 * ReentrantLock是唯一实现了Lock接口的类，并且ReentrantLock提供了更多的方法。
 * 下面通过一些实例看具体看一下如何使用ReentrantLock。
 */
public class LockI {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public static void main(String[] args)  {
        final LockI lockI = new LockI();

        new Thread(){
            public void run() {
                lockI.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                lockI.insert(Thread.currentThread());
            };
        }.start();
    }

    //静态锁
    static Lock lock = new ReentrantLock();
    public void insert(Thread thread) {
        //对象锁
        //Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }
}
