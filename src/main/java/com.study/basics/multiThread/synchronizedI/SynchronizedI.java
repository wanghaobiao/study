package com.study.basics.multiThread.synchronizedI;

public class SynchronizedI {
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
    /**
     * 锁静态对象时所有实例运行到该处都会被锁住（类级别的锁），
     * 而锁实例对象的时候，该锁只对每个实例有效（实例级别的锁）。
     * 所以如果该锁静态对象的时候，锁了实例对象，会造成该锁无效；如果在该锁实例对象的时候，锁了静态对象，会造成很大的性能问题。
     */
    private static Object lock = new Object();
    private static void method01() throws InterruptedException {

        Thread t1 = new Thread( () -> {
            /**
             * 修饰的这个代码块  加了锁
             * 如果是两个代码块拥有同一把锁
             * 只要等拿到锁的执行完自动释放锁以后另一个代码块才会执行
             */
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
            System.out.println("线程1执行完毕");
        } );



        Thread t2 = new Thread( () -> {
            //代表锁
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
            System.out.println("线程2执行完毕");
        } );


        t2.start();
        t1.start();
    }

    public static void main(String[] args) throws InterruptedException {
        method01();
    }
}
