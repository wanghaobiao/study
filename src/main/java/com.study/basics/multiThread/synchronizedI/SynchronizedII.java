package com.study.basics.multiThread.synchronizedI;

public class SynchronizedII implements Runnable {
    //共享资源
    static int i =0;
    /**
     * 如果使用synchronized修饰(加锁)的话 就不会出现线程安全问题  最后的结果为 2000
     * 如果不使用synchronized修饰(加锁)的话 就会出现线程安全问题  最后的结果可能小于 2000
     */
    public synchronized void increase(){
        for (int j = 0; j < 10000; j++) {
            synchronized (this){
                i++;
            }
        }
    }
    @Override
    public void run(){
        increase();
        for (int j = 0; j < 10000; j++) {
            synchronized (this){
                i++;
            }
        }
        System.out.println(i);

    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedII test = new SynchronizedII();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();
        t2.start();
        /*t1.join();
        t2.join();*/
        System.out.println(i);
    }
}
