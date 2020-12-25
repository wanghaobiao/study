package com.study.pattern.decoratorPattern;

public class ManDecoratorB extends Decorator {

    @Override
    public void eat() {
        System.out.println("医生B这个角色在吃");
        super.eat();
    }
}
