package com.study.basics.multiThread.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * lockInterruptibly()响应中断的使用方法：
 */
public class lockIII {
    private Lock lock = new ReentrantLock();
    public static void main(String[] args)  {
        lockIII lockIII = new lockIII();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                try {
                    lockIII.insert(Thread.currentThread());
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+"被中断");
                }
            };
        };
        thread1.start();
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                try {
                    lockIII.insert(Thread.currentThread());
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+"被中断");
                }
            };
        };
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException{
        //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        lock.lockInterruptibly();
        try {
            System.out.println(thread.getName()+"得到了锁");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE){
                    break;
                    //插入数据
                }
            }
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }
    }
}

