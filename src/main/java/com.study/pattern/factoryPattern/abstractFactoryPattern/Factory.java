package com.study.pattern.factoryPattern.abstractFactoryPattern;

public class Factory implements  IFactory {
    @Override
    public IProduct1 createProduct1() {
        return new Product1();
    }
    @Override
    public IProduct2 createProduct2() {
        return new Product2();
    }
}
