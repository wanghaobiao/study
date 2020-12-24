package com.study.basics.serializable.serializableI;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 序列化
 */
public class WriteObject {
    public static void main(String[] args) {
        try (
            //创建一个ObjectOutputStream输出流
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("xxx",123);
            //将对象序列化到文件
            Person person = new Person("9龙", 23,temp);
            oos.writeObject(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
