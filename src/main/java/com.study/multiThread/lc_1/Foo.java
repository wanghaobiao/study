package com.study.multiThread.lc_1;

import java.util.concurrent.Semaphore;

public class Foo {
    public Semaphore seam1to2 = new Semaphore(0);
    public Semaphore seam2to3 = new Semaphore(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        seam1to2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        seam1to2.acquire();
        printSecond.run();
        seam2to3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        seam2to3.acquire();
        printThird.run();
    }
}
