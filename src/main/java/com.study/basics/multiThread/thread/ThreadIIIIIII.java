package com.study.basics.multiThread.thread;

import com.server.basis.util.DateUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadIIIIIII {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
            };
        }.start();
        Thread thread1 = new Thread( () -> {
            Date startDate = new Date();
        } );
        thread1.start();

        Thread thread2 = new Thread(new Runnable(){
            @Override
            public void run(){
                Date startDate = new Date();
            }
        });
        thread2.start();

        FutureTask<String> futureTask1 = new FutureTask<>( () -> {   //重写Callable接口中的call()方法
            Date startDate = new Date();
            return "cell1";
        } );

        FutureTask<String> futureTask2 = new FutureTask<String>(new Callable<String>(){
            @Override
            public String call() throws Exception {   //重写Callable接口中的call()方法
                Date startDate = new Date();
                return "cell1";
            }
        });
        Thread callableThread = new Thread(futureTask2);
        callableThread.start();
    }

}

//1.继承Thread类型重写run 方法
class ThreadDemoTest extends Thread{
    @Override
    public void run() {
        System.out.println("通过继承Thread类重写run方法实现接口！");
    }
    public static void main(String[] args) {
        ThreadDemoTest threadDemoTest = new ThreadDemoTest();
        threadDemoTest.run();
    }
}

//2.实现Runnable接口
class RunnableDemoTest implements Runnable{
    @Override
    public void run() {
        System.out.println("实现Runnable开启线程！");
    }
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableDemoTest());
        thread.start();
    }
}

//3.实现Callable接口
class CallableDemoTest implements Callable {
    @Override
    public Object call() {
        return "HelloCallable!";
    }
    @Test
    public void test() throws ExecutionException, InterruptedException {
        CallableDemoTest callableDemoTest = new CallableDemoTest();
        FutureTask futureTask = new FutureTask(callableDemoTest);
        Thread thread = new Thread(futureTask);
        thread.start();
        //获取返回值
        futureTask.get();
    }
}
