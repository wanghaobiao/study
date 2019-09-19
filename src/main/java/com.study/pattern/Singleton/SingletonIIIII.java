package com.study.pattern.Singleton;
//静态内部类
public class SingletonIIIII {
    private static class SingletonHolder {

        private static final SingletonIIIII INSTANCE = new SingletonIIIII();

    }

    private SingletonIIIII (){}

    public static final SingletonIIIII getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
