package com.study.basics.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 泛型
* */
public class GenericMethod {
    public <T> void printValue(T t){
        System.out.println("进入普通泛型----->" + t);
    }
    public <CD extends List> void printValue(CD t){
        System.out.println("进入List泛型----->" + t);
    }
    public <T extends Map> void printValue1(T t){
        System.out.println("进入Map泛型----->" + t);
    }

    /**
     * 泛型的定义 像变量一样  可以为任意值
     */
    public static void main(String[] args) {
        GenericMethod gm = new GenericMethod();
        gm.printValue( "hello" );
        gm.printValue( 123 );
        gm.printValue( 5.0f );

        gm.printValue( new ArrayList() );

        gm.printValue1( new HashMap<>() );

        List<String> list = new ArrayList<>();
    }


}
