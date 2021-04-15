package com.study.pattern.singleton;

import com.server.basis.util.DateUtil;
import com.server.basis.util.PowerUtil;

//懒汉，线程安全
public class SingletonII {

    private static SingletonII instance;

    private SingletonII (){}

    public static synchronized SingletonII getInstance() {
        if (instance == null) {
            instance = new SingletonII();
        }
        return instance;
        //PowerUtil.getDate( );

    }
}
