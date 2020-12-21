package com.study.pattern.singleton;
//饿汉，变种
public class SingletonIIII {
    private static SingletonIIII instance = null;

    static {
        instance = new SingletonIIII();
    }

    private SingletonIIII (){}

    public static SingletonIIII getInstance() {
        return instance;
    }
}
