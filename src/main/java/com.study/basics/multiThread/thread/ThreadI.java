package com.study.basics.multiThread.thread;

import com.server.basis.util.DateUtil;

import java.util.Date;

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
        Date startDate1 = new Date();
        Thread t1 = new Thread( () -> {
            Date startDate = new Date();
            System.out.println( "t1 is 开始 时间" + DateUtil.toString( startDate,DateUtil.DATE_LONG ) );
            try {
                Thread.sleep(2000);
                System.out.println(i++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( "t1 is 完成" );
            System.out.println("=================>t1共耗时"+(System.currentTimeMillis()  - startDate.getTime())+"毫秒");
        } );
        Thread t2 = new Thread( () -> {
            Date startDate = new Date();
            System.out.println( "t2 is 开始 时间" + DateUtil.toString( startDate,DateUtil.DATE_LONG ) );
            try {
                Thread.sleep(4000);
                System.out.println(i++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( "t2 is 完成" );
            System.out.println("=================>t2共耗时"+(System.currentTimeMillis()  - startDate.getTime())+"毫秒");
        } );
        /*Thread t3 = new Thread( () -> {
            Date startDate = new Date();
            System.out.println( "t3 is 开始 时间" + DateUtil.toString( startDate,DateUtil.DATE_LONG ) );
            try {
                //表示等待线程1执行后,执行
                t1.join();
                //表示等待线程2执行后,执行
                t2.join();
                Thread.sleep(3000);
                System.out.println(i++);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( "t3 is 完成" );
            System.out.println("=================>t2共耗时"+(System.currentTimeMillis()  - startDate.getTime())+"毫秒");
        } );
        t3.start();*/
        t2.start();
        t1.start();
        //表示等待线程1执行后,执行
        try {
            t1.join();
            //表示等待线程2执行后,执行
            t2.join();
            System.out.println("=================>根节点共耗时"+(System.currentTimeMillis()  - startDate1.getTime())+"毫秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * 第二种实现方式，线程执行顺序可以在方法中调换
     */
    private static void method02() {
        Date startDate = new Date();
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
        System.out.println("=================>共耗时"+(System.currentTimeMillis()  - startDate.getTime())+"毫秒");
    }
}
