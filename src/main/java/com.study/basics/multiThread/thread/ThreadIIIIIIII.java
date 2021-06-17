package com.study.basics.multiThread.thread;
/**
 * ThreadLocal 是线程本地存储，在每个线程中都创建了一个 ThreadLocalMap 对象，每个线程可以访问自己内部 ThreadLocalMap 对象内的 value。
 * 经典的使用场景是为每个线程分配一个 JDBC 连接 Connection。这样就可以保证每个线程的都在各自的 Connection 上进行数据库的操作，
 * 不会出现 A 线程关了 B线程正在使用的 Connection； 还有 Session 管理 等问题。
 * https://blog.csdn.net/meism5/article/details/90413860?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522162322275316780262529234%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=162322275316780262529234&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-90413860.first_rank_v2_pc_rank_v29&utm_term=ThreadLocal+%E6%98%AF%E4%BB%80%E4%B9%88%EF%BC%9F%E6%9C%89%E5%93%AA%E4%BA%9B%E4%BD%BF%E7%94%A8%E5%9C%BA%E6%99%AF%EF%BC%9F&spm=1018.2226.3001.4187
 */
public class ThreadIIIIIIII {


    //线程本地存储变量
    private static final ThreadLocal<Integer> THREAD_LOCAL_NUM = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    /**
     * 线程本地存储变量加 5
     */
    private static void add10ByThreadLocal() {
        for (int i = 0; i < 5; i++) {
            Integer n = THREAD_LOCAL_NUM.get();
            n += 1;
            THREAD_LOCAL_NUM.set(n);
            System.out.println(Thread.currentThread().getName() + " : ThreadLocal num=" + n);
        }
    }

    public static void main(String[] args) {
        //启动三个线程
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    add10ByThreadLocal();
                }
            };
            t.start();
        }
    }


}
