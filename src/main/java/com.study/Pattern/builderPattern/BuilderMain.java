package com.study.pattern.builderPattern;

import java.util.HashMap;
import java.util.Map;

public class BuilderMain {
    public Product constructProduct(ConcreteBuilder concreteBuilder){
        concreteBuilder.buildBasic();
        concreteBuilder.buildWalls();
        concreteBuilder.roofed();
        return concreteBuilder.buildProduct();
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        BuilderMain builder = new BuilderMain();
        Product product = builder.constructProduct(new ConcreteBuilder());
        System.out.println( product.getBasic());

    }
}
