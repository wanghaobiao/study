package com.study.basics.multiThread.thread;
/**
 * thread.Join  用法
 */
public class ThreadI {
    /**
     * thread.Join 的用法
     * 把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
     * 比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
     * 想要更深入了解，建议看一下join的源码，也很简单的，使用wait方法实现的。
     * t.join(); //调用join方法，等待线程t执行完毕
     * t.join(1000); //等待 t 线程，等待时间是1000毫秒。
     */
    public static void main(String[] args) {
        method01();
        //method02();
    }

    /**
     * 第一种实现方式，顺序写死在线程代码的内部了，有时候不方便
     */
    static Integer i = 0;
    private static void method01() {

        Thread t1 = new Thread( () -> {
            try {
                Thread.sleep(1000);
                System.out.println(i++);
                System.out.println( "t1 is ing" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( "t1 is finished" );
        } );
        Thread t2 = new Thread( () -> {
            try {
                Thread.sleep(2000);
                //表示等待线程1执行后,执行
                t1.join();
                System.out.println(i++);
                System.out.println( "t2 is ing" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( "t2 is finished" );
        } );
        Thread t3 = new Thread( () -> {
            try {
                Thread.sleep(3000);
                //表示等待线程2执行后,执行
                t2.join();
                System.out.println(i++);
                System.out.println( "t3 is ing" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( "t3 is finished" );
        } );

        t3.start();
        t2.start();
        t1.start();
    }


    /**
     * 第二种实现方式，线程执行顺序可以在方法中调换
     */
    private static void method02() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(i++);
                System.out.println(Thread.currentThread().getName() + "执行完成" );
            }
        };
        Thread t1 = new Thread( runnable, "t1" );
        Thread t2 = new Thread( runnable, "t2" );
        Thread t3 = new Thread( runnable, "t3" );
        t1.start();
        //t1.join();
        t2.start();
        //t2.join();
        t3.start();
        //t3.join();
    }
}
