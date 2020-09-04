package com.study.multiThread.deadlock;

import java.util.concurrent.Semaphore;

/**
 * wait()   notify()
 */
public class ProducerAndConsumer2 {
    public static void main(String[] args) {
        StoreHouse2 storeHouse = new StoreHouse2();
        Thread producer1 = new Producer2( storeHouse,"生产者1" );
        Thread producer2 = new Producer2( storeHouse,"生产者2" );
        Thread producer3 = new Producer2( storeHouse,"生产者3" );
        Thread consumer1 = new Consumer2( storeHouse,"消费者1" );
        Thread consumer2 = new Consumer2( storeHouse,"消费者2" );
        Thread consumer3 = new Consumer2( storeHouse,"消费者3" );
        producer1.start();
        producer2.start();
        producer3.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }
}
class Sem {
    public static final Semaphore MUTEX = new Semaphore( 1 );
    public static final Semaphore CANPUT = new Semaphore( 10 );
    public static final Semaphore CANGET = new Semaphore( 0 );
}

class StoreHouse2 {
    private int[] storeRack = new int[10];
    private int putIndex = 0;
    private int getIndex = 0;
    private int data = 0;


    public  void put() {
        storeRack[putIndex] = data;
        System.out.println(Thread.currentThread().getName()+"向货架【"+putIndex+"】位置处放入数据："+data);
        putIndex=(putIndex+1)%storeRack.length;
        data++;

    }
    public void get(){
        int product = storeRack[getIndex];
        System.out.println( "                                "+Thread.currentThread().getName()+"从货架【" + getIndex + "】位置取出数据：" + product );
        getIndex = (getIndex + 1) % storeRack.length;
    }
}

class Producer2 extends Thread{
    private StoreHouse2 store;
    Producer2(StoreHouse2 store,String name) {
        super(name);
        this.store = store;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep( 250 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Sem.CANPUT.acquire();//获取放入仓库产品许可
                Sem.MUTEX.acquire();//获取仓库操作许可
                store.put(  );//将产品放入仓库
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                Sem.MUTEX.release();//释放仓库操作许可
                Sem.CANGET.release();//释放放入仓库产品许可
            }


        }
    }
}

class Consumer2 extends Thread{
    private StoreHouse2 store;
    Consumer2(StoreHouse2 store,String name) {
        super(name);
        this.store = store;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep( 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Sem.CANGET.acquire();//获取放入仓库产品许可
                Sem.MUTEX.acquire();//获取仓库操作许可
                store.get(  );//将产品放入仓库
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                Sem.MUTEX.release();//释放仓库操作许可
                Sem.CANPUT.release();//释放放入仓库产品许可
            }
        }
    }

}
