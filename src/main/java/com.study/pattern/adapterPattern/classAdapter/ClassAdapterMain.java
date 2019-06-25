package com.study.pattern.adapterPattern.classAdapter;
/*
*
* 对象适配器
*
* */
public class ClassAdapterMain {
    public static void main(String[] args) {
        Ps2 p = new Adapter();
        p.isPs2();
    }
}
