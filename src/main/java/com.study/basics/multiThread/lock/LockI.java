package com.study.basics.multiThread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ReentrantLock
 * 意思是“可重入锁”
 * 关于可重入锁的概念在下一节讲述。
 * ReentrantLock是唯一实现了Lock接口的类，并且ReentrantLock提供了更多的方法。
 * 下面通过一些实例看具体看一下如何使用ReentrantLock。
 * Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
 * synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；
 * 而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
 * Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
 * 通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
 * Lock可以提高多个线程进行读操作的效率。
 * 在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。所以说，在具体使用时要根据适当情况选择。
 */
public class LockI {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public static void main(String[] args)  {
        final LockI lockI = new LockI();

        new Thread(){
            public void run() {
                lockI.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                lockI.insert(Thread.currentThread());
            };
        }.start();
    }

    //静态锁
    static Lock lock = new ReentrantLock();
    public void insert(Thread thread) {
        //对象锁
        //Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }
}
