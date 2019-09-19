package com.study.pattern.statesPattern;

public class Ordinary implements LuFeiState{

    @Override
    public void change() {
        System.out.println("路飞当前为普通状态战斗");
    }

}
