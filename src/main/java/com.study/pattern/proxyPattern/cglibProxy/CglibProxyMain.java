package com.study.pattern.proxyPattern.cglibProxy;

import com.study.pattern.proxyPattern.JDKProxy.Singer;

/*
* cglib代理模式
*
* */
public class CglibProxyMain {
    public static void main(String[] args){
        //目标对象
        Singer target = new Singer();
        //代理对象
        Singer proxy = (Singer)new ProxyFactory(target).getProxyInstance();
        //执行代理对象的方法
        proxy.sing();
    }
}
