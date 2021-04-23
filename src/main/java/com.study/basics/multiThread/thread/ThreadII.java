package com.study.basics.multiThread.thread;
/**
 * 线程的 run()和 start()有什么区别
 */
public class ThreadII {

    public static void main(String[] args){
        Thread t1 = new Thread( () -> {  test(); });
        Thread t2 = new Thread( () -> {  test(); });
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        /**
         * 这两个线程都是使用start方法开启的，
         * 所以并不需要等待另一个完成，
         * 所以他们的执行顺序应该是并行的
         */
        //thread1.start();
        //thread2.start();
        /**
         * 直接使用对象调用方法，
         * 那必须是这个方法执行完了代码才能往下走
         */
        thread1.run();
        thread2.run();
    }

    public static void test() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

