package com.study.pattern.bridgePattern;
/*
*
* 桥梁模式
*
* */
public class bridgeMain {
    public static void main(String[] args) {
        AreaA a = new AreaA2();
        a.qiao = new AreaB3();
        a.fromAreaA();
        a.qiao.targetAreaB();
    }
}
