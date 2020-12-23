package com.study.basics.multiThread.synchronizedI;

public class SynchronizedIII {
    //可以通过 synchronized 去除和增加看效果
    public synchronized void method1() {
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }
    //修饰方法 表示当前的对象锁
    public  void method2() {
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }


    public static void main(String[] args) {
        final SynchronizedIII test = new SynchronizedIII();
        new Thread(test::method1).start();
        new Thread(test::method2).start();
    }
}
