package com.study.pattern.factoryPattern.abstractFactoryPattern;

public class ClientMain {
    public static void main(String[] args){
        IFactory factory = new Factory();
        factory.createProduct1().show();
        factory.createProduct2().show();
    }
}
