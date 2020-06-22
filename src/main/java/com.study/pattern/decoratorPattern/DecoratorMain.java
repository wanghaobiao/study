package com.study.pattern.decoratorPattern;
/*
*
* 装饰模式
*
* */
public class DecoratorMain {
    public static void main(String[] args) {
        Man man = new Man();
        ManDecoratorA manDecoratorA = new ManDecoratorA();
        ManDecoratorB manDecoratorB = new ManDecoratorB();

        manDecoratorA.setPerson(man);
        manDecoratorB.setPerson(manDecoratorA);
        manDecoratorB.eat();
    }
}
