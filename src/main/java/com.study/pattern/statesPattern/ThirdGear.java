package com.study.pattern.statesPattern;

public class ThirdGear implements LuFeiState{
    @Override
    public void change() {
        System.out.println("路飞开启三挡战斗");
    }
}
