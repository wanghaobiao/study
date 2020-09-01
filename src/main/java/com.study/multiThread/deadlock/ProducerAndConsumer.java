package com.study.multiThread.deadlock;
/**
 * wait()   notify()
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        StoreHouse storeHouse = new StoreHouse();
        Producer producer1 = new Producer( storeHouse,"生产者1" );
        Producer producer2 = new Producer( storeHouse,"生产者2" );
        Producer producer3 = new Producer( storeHouse,"生产者3" );
        Consumer consumer1 = new Consumer( storeHouse,"消费者1" );
        Consumer consumer2 = new Consumer( storeHouse,"消费者2" );
        Consumer consumer3 = new Consumer( storeHouse,"消费者3" );
        producer1.start();
        producer2.start();
        producer3.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
    }
}
class StoreHouse {
    private int[] storeRack = new int[10];
    private int putIndex = 0;
    private int getIndex = 0;
    //仓库中产品的数量
    private int dataCout = 0;
    private int data = 0;


    public synchronized void put() {
        while (dataCout == storeRack.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storeRack[putIndex] = data;
        System.out.println(Thread.currentThread().getName()+"向货架【"+putIndex+"】位置处放入数据："+data);
        putIndex=(putIndex+1)%storeRack.length;
        data++;
        dataCout ++;
        this.notify();

    }
    public synchronized void get(){
        while (dataCout == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int product = storeRack[getIndex];
        System.out.println( "                                "+Thread.currentThread().getName()+"从货架【" + getIndex + "】位置取出数据：" + product );
        getIndex = (getIndex + 1) % storeRack.length;
        dataCout--;
        this.notify();
    }
}
class Producer extends Thread{
    private StoreHouse store;
    Producer(StoreHouse store,String name) {
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
            store.put(  );
        }
    }
}
class Consumer extends Thread{
    private StoreHouse store;
    Consumer(StoreHouse store,String name) {
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
            store.get( );
        }
    }

}
