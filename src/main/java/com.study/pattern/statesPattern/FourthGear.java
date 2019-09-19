package com.study.pattern.statesPattern;

public class FourthGear implements LuFeiState{
    @Override
    public void change() {
        System.out.println("路飞开启四挡战斗");
    }
}