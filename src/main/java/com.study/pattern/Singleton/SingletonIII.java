package com.study.pattern.Singleton;
//饿汉
public class SingletonIII {
    private static SingletonIII instance = new SingletonIII();

    private SingletonIII (){}

    public static SingletonIII getInstance() {
        return instance;
    }
}
