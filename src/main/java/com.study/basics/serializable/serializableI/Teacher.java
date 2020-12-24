package com.study.basics.serializable.serializableI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Teacher implements Serializable {

    private String name;
    private Person person;

    public Teacher(String name, Person person) {
        this.name = name;
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static void main(String[] args) throws Exception {
        try (
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("teacher.txt"))) {
            Person person = new Person("路飞", 20,null);
            Teacher t1 = new Teacher("雷利", person);
            Teacher t2 = new Teacher("红发香克斯", person);
            //依次将4个对象写入输入流
            stream.writeObject(t1);
            stream.writeObject(t2);
            stream.writeObject(person);
            stream.writeObject(t2);

            List<Map<String, Object>> list = new ArrayList<>();
        }
    }
}
