package com.study.basics.generic;

import com.server.basis.util.MapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*
* 泛型
* */
public class GenericMethod {
    public <T> void printValue(T t){
        System.out.println(t);
    }
    public <GD extends List> void printList(GD t){
        System.out.println(t);
    }



    public <T extends Map> void printList1(T t){
        System.out.println(t);
    }

    /**
     * 泛型的定义 像变量一样  可以为任意值
     */
    public static void main(String[] args) {
        GenericMethod gm = new GenericMethod();
        gm.printValue( "hello" );
        gm.printValue( 123 );
        gm.printValue( 5.0f );

        gm.printList( new ArrayList() );

        List<String> list = new ArrayList<>();
    }


}
