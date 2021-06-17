package com.study.basics.multiThread.thread;
/**
 * volatile和synchronized的区别
 * （1）每个线程都有自己的本地内存空间（java栈中的帧）。线程执行时，先把变量从内存读到线程自己的本地内存空间，然后对变量进行操作。
 * （2）对该变量操作完成后，在某个时间再把变量刷新回主内存。
 *
 *  那么我们再了解下锁提供的两种特性：互斥（mutual exclusion） 和可见性（visibility）：
 * （1）互斥（mutual exclusion）：互斥即一次只允许一个线程持有某个特定的锁，因此可使用该特性实现对共享数据的协调访问协议，这样，一次就只有一个线程能够使用该共享数据；
 * （2）可见性（visibility）：简单来说就是一个线程修改了变量，其他线程可以立即知道。保证可见性的方法：volatile,synchronized,final(一旦初始化完成其他线程就可见)。
 */
public class ThreadIIIIII extends Thread {

    /**
     * 原理
     * 当对volatile标记的变量进行修改时，会将其他缓存中存储的修改前的变量清除，然后重新读取。
     * 一般来说应该是先在进行修改的缓存A中修改为新值，然后通知其他缓存清除掉此变量，
     * 当其他缓存B中的线程读取此变量时，会向总线发送消息，这时存储新值的缓存A获取到消息，将新值传给B。
     * 最后将新值写入内存。当变量需要更新时都是此步骤，
     * volatile的作用是被其修饰的变量，每次更新时，都会刷新上述步骤。
     */
    volatile private boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        System.out.println( "进入到run方法中了" );
        while (isRunning == true) {
        }
        System.out.println( "线程执行完成了" );
    }

    /**
     *
     * 在main线程中，thread.setRunning(false);
     * 将启动的线程RunThread中的共享变量设置为false，从而想让RunThread.java的while循环结束。
     * 如果使用JVM -server参数执行该程序时，RunThread线程并不会终止，从而出现了死循环。
     *
     * 原因分析
     * 现在有两个线程，一个是main线程，另一个是RunThread。
     * 它们都试图修改isRunning变量。按照JVM内存模型，main线程将isRunning读取到本地线程内存空间，修改后，再刷新回主内存。
     *
     * 而在JVM设置成 -server模式运行程序时，
     * 线程会一直在私有堆栈中读取isRunning变量。
     * 因此，RunThread线程无法读到main线程改变的isRunning变量。从而出现了死循环，导致RunThread无法终止。
     *
     */
    public static void main(String[] args) {
        try {
            ThreadIIIIII thread = new ThreadIIIIII();
            thread.start();
            Thread.sleep( 1000 );
            thread.setRunning( false );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
