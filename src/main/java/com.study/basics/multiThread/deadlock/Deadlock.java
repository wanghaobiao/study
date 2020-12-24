package com.study.basics.multiThread.deadlock;

import java.util.concurrent.Semaphore;

/**
 * 死锁问题
 * synchronized  嵌套容易造成死锁
 */
public class Deadlock {

    public static final Object A = new Object();
    public static final Object B = new Object();

    public static final Semaphore SIGANL = new Semaphore( 1 );

    public static void main(String[] args){
        Thread x = new Thread( () -> {
            try {
                SIGANL.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (A){
                System.out.println("线程X已经获得了锁A");
                synchronized (B){
                    System.out.println("线程X已经获得了锁B");
                }
            }
            System.out.println("线程X执行完毕");
            SIGANL.release();
        });

        Thread y = new Thread( () -> {
            try {
                SIGANL.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (B){
                System.out.println("线程Y已经获得了锁B");
                synchronized (A){
                    System.out.println("线程Y已经获得了锁Y");
                }
            }
            System.out.println("线程Y执行完毕");
            SIGANL.release();
        });
        x.start();
        y.start();
        System.out.println(x.getState());
        System.out.println(y.getState());
    }
}
