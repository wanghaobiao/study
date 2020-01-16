package com.study.basics.serializable.serializable1;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReadTeacher {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teacher.txt"))) {
            Teacher t1 = (Teacher) ois.readObject();
            Teacher t2 = (Teacher) ois.readObject();
            Person p = (Person) ois.readObject();
            Teacher t3 = (Teacher) ois.readObject();
            System.out.println(t1 == t2);
            System.out.println(t1.getPerson() == p);
            System.out.println(t2.getPerson() == p);
            System.out.println(t2 == t3);
            System.out.println(t1.getPerson() == t2.getPerson());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
