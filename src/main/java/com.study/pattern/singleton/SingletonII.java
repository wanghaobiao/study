package com.study.pattern.singleton;
//懒汉，线程安全
public class SingletonII {

    private static SingletonII instance;

    private SingletonII (){}

    public static synchronized SingletonII getInstance() {
        if (instance == null) {
            instance = new SingletonII();
        }
        return instance;
    }
}
