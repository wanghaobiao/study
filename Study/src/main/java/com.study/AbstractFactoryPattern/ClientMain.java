package com.study.AbstractFactoryPattern;

public class ClientMain {
    public static void main(String[] args){
        IFactory factory = new Factory();
        factory.createProduct1().show();
        factory.createProduct2().show();

    }
}
