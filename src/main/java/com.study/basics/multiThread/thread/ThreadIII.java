package com.study.basics.multiThread.thread;
/**
 * sleep() 和 wait() 有什么区别？
 */
public class ThreadIII {
    /**
     * sleep方法只让出了CPU，而并不会释放同步资源锁
     * wait方法则是指当前线程让自己暂时退让出同步资源锁，以便其他正在等待该资源的线程得到该资源进而运行
     */
    public static void main(String[] args) {
        Thread t1 = new Thread( () -> {
            synchronized (ThreadIII.class) {
                System.out.println("thread1进入...");
                System.out.println("thread1 Wait...");
                try {
                    //调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
                    ThreadIII.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("========================>thread1被唤醒了");
                System.out.println("thread1结束!!!");
            }
        });
        t1.start();
        Thread t2 = new Thread( () -> {
            synchronized (ThreadIII.class) {
                System.out.println("thread2进入...");
                System.out.println("thread2 Wait...");
                try {
                    //调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
                    ThreadIII.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("========================>thread2被唤醒了");
                System.out.println("thread2结束!!!");
            }
        });
        t2.start();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread t3 = new Thread( () -> {
            synchronized (ThreadIII.class) {
                System.out.println("thread3进入...");
                System.out.println("thread3唤醒一个线程");
                //只有针对此对象调用notify()方法后本线程才进入对象锁定池准备获取对象锁进入运行状态。
                ThreadIII.class.notifyAll();
                //==================
                //区别
                //如果我们把代码：TestD.class.notify();给注释掉，即TestD.class调用了wait()方法，但是没有调用notify()
                //方法，则线程永远处于挂起状态。
            }

            /*try {
                //sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，
                //但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
                //在调用sleep()方法的过程中，线程不会释放对象锁。
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            System.out.println("thread3正在进行....");
            System.out.println("thread3结束!!!");
        });
        t3.start();
    }
}
