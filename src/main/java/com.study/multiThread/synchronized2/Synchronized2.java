package com.study.multiThread.synchronized2;

public class Synchronized2 {
    /*private void method02(){
        synchronized (this){}
    }
    上边等同于下边  用synchronized修饰一个方法的时候  锁的就是this 当前对象
    private synchronized void method02(){
    }*/

    /*private static synchronized void method02(){
    }
    等同于
    private static void method02(){
        synchronized (Synchronized2.class){}
    }*/

    //也可以使用this替代
    private static Object lock = new Object();
    private static void method01() throws InterruptedException {

        Thread t1 = new Thread( () -> {
            synchronized (lock){
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep( 100 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println( "线程1在执行" );
                }
            }
        } );



        Thread t2 = new Thread( () -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep( 100 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println( "线程2在执行" );
                }
            }
        } );


        t1.start();
        t2.start();
    }

    public static void main(String[] args) throws InterruptedException {
        method01();
    }
}
