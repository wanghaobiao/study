package com.study.pattern.decoratorPattern;

public class ManDecoratorA extends Decorator {

    @Override
    public void eat() {
        System.out.println("医生A这个角色在吃");
        super.eat();
        reEat();
    }

    public void reEat() {
        System.out.println("再吃一顿饭");
    }
}
