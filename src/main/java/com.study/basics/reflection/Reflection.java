package com.study.basics.reflection;

public class Reflection {


    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;
        //获取类的三种方式
        class1 = Class.forName( "com.study.basics.reflection.Reflection" );
        class2 = new Reflection().getClass();
        class3 = Reflection.class;
        System.out.println("类名称   " + class1.getName());
        System.out.println("类名称   " + class2.getName());
        System.out.println("类名称   " + class3.getName());
        System.out.println("=======================================");
        Reflection reflection = new Reflection();
        //class com.example.demo.DemoApplication
        System.out.println( reflection.getClass());
        //com.example.demo.DemoApplication
        System.out.println( reflection.getClass().getName());
        //class com.example.demo.DemoApplication
        System.out.println( Reflection.class);
        System.out.println(Class.forName("com.study.basics.reflection.Reflection"));



    }

}
