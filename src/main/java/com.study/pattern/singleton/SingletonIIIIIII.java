package com.study.pattern.singleton;

public class SingletonIIIIIII {
    private volatile static SingletonIIIIIII singleton;

    private SingletonIIIIIII (){}

    public static SingletonIIIIIII getSingleton() {
        if (singleton == null) {
            synchronized (SingletonIIIIIII.class) {
                if (singleton == null) {
                        singleton = new SingletonIIIIIII();
                }
            }
        }
        return singleton;
    }

}
