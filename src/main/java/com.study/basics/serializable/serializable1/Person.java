package com.study.basics.serializable.serializable1;

import java.io.Serializable;
import java.util.Map;

/**
 * 序列化
 */
public class Person implements Serializable {
    private String name;
    private int age;
    private Map<String, Object> temp ;

    //我不提供无参构造器
    public Person(String name, int age,Map<String, Object> temp ) {
        this.name = name;
        this.age = age;
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                "map='" + temp.get("xxx") + '\'' +
                '}';
    }
}

