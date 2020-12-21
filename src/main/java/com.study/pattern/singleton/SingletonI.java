package com.study.pattern.singleton;
//懒汉，线程不安全
public class SingletonI {
    private static SingletonI instance;
    private SingletonI (){}
    public static SingletonI getInstance() {
        if (instance == null) {
            instance = new SingletonI();
        }
        return instance;
    }
}
