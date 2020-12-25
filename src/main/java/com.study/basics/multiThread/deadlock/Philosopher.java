package com.study.basics.multiThread.deadlock;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
    public static final Semaphore SIGANL = new Semaphore( 4 );

    //1...思考问题
    public int index;
    public Object[] forks;
    public Philosopher(int index,Object[] forks){
        this.index = index;
        this.forks = forks;
    }
    @Override
    public void run() {
        while (true) {
            try {
                SIGANL.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( index + "号哲学家正在思考问题...." );
            synchronized (forks[index]) {
                System.out.println( index + "号暂学家掌起左叉子....." );
                synchronized (forks[(index + 1) % forks.length]) {
                    System.out.println( index + "号哲学家章起右叉子....." );
                    System.out.println( index + "号哲学家正在吃饭......" );
                }
                System.out.println( index + "号暂学家放下右叉子......");
            }
            System.out.println( index + "号哲学家放下左叉子..." );
            SIGANL.release();
        }
    }

    public static void main(String[] args) {
        Object[] forks = new Object[]{new Object(),new Object(),new Object(),new Object(),new Object()};
        Philosopher p0 = new Philosopher( 0, forks);
        Philosopher p1 = new Philosopher( 1, forks);
        Philosopher p2 = new Philosopher( 2, forks);
        Philosopher p3 = new Philosopher( 3, forks);
        Philosopher p4 = new Philosopher( 4, forks);
        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();

    }
}

