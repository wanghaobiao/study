package com.study.basics.multiThread.thread;

import com.server.basis.util.DateUtil;

import javax.imageio.ImageTranscoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public static void main(String[] args) throws InterruptedException {
        method01();
        //method02();
    }


    /**
     * 第一种实现方式，顺序写死在线程代码的内部了，有时候不方便
     */
    static Integer i = 0;
    private static void method01() throws InterruptedException {
        Date startDate1 = new Date();

        List<Thread> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            Thread thread = new Thread( () -> {
                Date startDate = new Date();
                getTest(startDate, finalI * 1000 );
            } );
            list.add( thread );
        }

        for (int i = 0; i < list.size(); i++) {
            Thread temp = list.get(i);
            temp.start();
        }
        for (int i = 0; i < list.size(); i++) {
            Thread temp = list.get(i);
            temp.join();
        }
        //加入  join  这句话在最后的方法执行之后才会执行
        //不加入  join  的区别在于  这句话的打印  会执行
        System.out.println("=================>根节点共耗时"+(System.currentTimeMillis()  - startDate1.getTime())+"毫秒");

    }

    private static void getTest(Date startDate,Integer sleep) {
        System.out.println( Thread.currentThread().getName() + " is 开始 时间" + DateUtil.toString( startDate,DateUtil.DATE_LONG ) );
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "共耗时"+(System.currentTimeMillis()  - startDate.getTime())+"毫秒");
    }


    /**
     * 第二种实现方式，线程执行顺序可以在方法中调换
     */
    private static void method02() throws InterruptedException {
        Date startMainDate = new Date();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Date startDate = new Date();
                try {
                    i++;
                    System.out.println( Thread.currentThread().getName() + "开始 时间" + DateUtil.toString( startDate,DateUtil.DATE_LONG ) );
                    Thread.sleep( i * 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "共耗时"+(System.currentTimeMillis()  - startDate.getTime())+"毫秒");
            }
        };
        Thread t1 = new Thread( runnable, "t1" );
        Thread t2 = new Thread( runnable, "t2" );
        Thread t3 = new Thread( runnable, "t3" );
        List<Thread> list = new ArrayList<>();
        list.add( t1 );
        list.add( t2 );
        list.add( t3 );
        for (int i = 0; i < list.size(); i++) {
            Thread temp = list.get(i);
            temp.start();

        }
        for (int i = 0; i < list.size(); i++) {
            Thread temp = list.get(i);
            temp.join();
        }
        //t1.start();
        //t1.join();
        //t2.start();
        //t2.join();
        //t3.start();
        //t3.join();
        System.out.println("=================>共耗时"+(System.currentTimeMillis()  - startMainDate.getTime())+"毫秒");
    }


}
