package com.study.pattern.mediatorPattern;

//定义抽象Mediator,可以与同时们进行联络
public abstract class Mediator {
    public abstract void contact(String content,Colleague coll);
}
