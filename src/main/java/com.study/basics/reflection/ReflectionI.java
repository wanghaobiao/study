package com.study.basics.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionI extends ReflectionII{
    private static String ReflectionIId = "1";


    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.study.reflection.ReflectionI");
        System.out.println("===============本类属性===============");
        // 取得本类的全部属性
        Field[] field_self = clazz.getDeclaredFields();
        for (int i = 0; i < field_self.length; i++) {
            // 权限修饰符
            int mo = field_self[i].getModifiers();
            String priv = Modifier.toString(mo);
            // 属性类型
            Class<?> type = field_self[i].getType();
            System.out.println(priv + " " + type.getName() + " " + field_self[i].getName() );
        }

        /****************************************通过反射机制调用对象方法开始****************************************/
        // 调用TestReflect类中的reflect1方法
        Method method = clazz.getMethod("reflect1");
        //两种实例化方法
        //ReflectionI reflectionI = new ReflectionI();
        ReflectionI reflectionI = (ReflectionI) clazz.newInstance();
        method.invoke(reflectionI);
        // Java 反射机制 - 调用某个类的方法1.
        // 调用TestReflect的reflect2方法
        method = clazz.getMethod("reflect2", int.class, String.class);
        method.invoke(clazz.newInstance(), 20, "张三");
        // Java 反射机制 - 调用某个类的方法2.
        // age -> 20. name -> 张三
        /****************************************通过反射机制调用对象方法结束***************************************/

        /**************************************通过反射机制操作某个类的属性开始**************************************/
        // 可以直接对 private 的属性赋值
        Field field = clazz.getDeclaredField("ReflectionIId");
        field.setAccessible(true);
        field.set(reflectionI, "2");
        System.out.println(field.get(reflectionI));
        // 可以直接对 对父类的对象操作属性赋值
        Field parentField = clazz.getField("ReflectionIIId");
        parentField.setAccessible(true);
        parentField.set(reflectionI, "3");
        System.out.println(parentField.get(reflectionI));
        /**************************************通过反射机制操作某个类的属性结束**************************************/

    }

    public void reflect1() {
        System.out.println("Java 反射机制 - 调用某个类的方法1.");
    }




}
