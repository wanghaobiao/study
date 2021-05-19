package com.study.basics.multiThread.synchronizedI;
/**
 * 作用于静态方法
 * 静态方法是依附于类而不是对象的，当synchronized修饰静态方法时，锁是class对象
 * 即使  new两个对象 锁也是生效的
 */
public class SynchronizedIIII  implements Runnable{
    //共享资源
    static int i =0;
    
    /**
     * 不用 static 修饰
     * 会出现资源抢夺  因为synchronized只作用于当前实例
     *
     */
    public synchronized void increase(){
        i++;
    }

    @Override
    public void run(){
        for (int j =0 ; j<10000;j++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new SynchronizedIIII());
        Thread t2 = new Thread(new SynchronizedIIII());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
