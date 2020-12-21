package com.study.basics.serializable.serializable1;


import java.io.FileInputStream;
import java.io.ObjectInputStream;
/**
 * 反序列化
 */
public class ReadObject {
    public static void main(String[] args) {
        try (
            //创建一个ObjectInputStream输入流
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
            Person brady = (Person) ois.readObject();
            System.out.println(brady);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
