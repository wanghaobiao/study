package com.study.multiThread.deadlock;

import java.util.concurrent.Semaphore;

/**
 * 死锁问题
 * synchronized  嵌套容易造成死锁
 */
public class Deadlock {

    public static void main(String[] args){
        //TODO Auto-generated method stub
        ThreadX x = new ThreadX();
        ThreadY y = new ThreadY();
        x.start();
        y.start();
        System.out.println(x.getState());
        System.out.println(y.getState());
    }
}


class  Mylock {
    public static final Object A = new Object();
    public static final Object B = new Object();
}
class Siganl1{
    public static final Semaphore SIGANL = new Semaphore( 1 );
}
class  ThreadX extends Thread{
    @Override
    public void run(){
        try {
            Siganl1.SIGANL.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (Mylock.A){
            System.out.println("线程X已经获得了锁A");
            synchronized (Mylock.B){
                System.out.println("线程X已经获得了锁B");
            }
        }
        System.out.println("线程X执行完毕");
        Siganl1.SIGANL.release();

    }
}
class  ThreadY extends Thread{
    @Override
    public void run(){
        try {
            Siganl1.SIGANL.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (Mylock.B){
            System.out.println("线程Y已经获得了锁B");
            synchronized (Mylock.A){
                System.out.println("线程Y已经获得了锁Y");
            }
        }
        System.out.println("线程Y执行完毕");
        Siganl1.SIGANL.release();
    }
}