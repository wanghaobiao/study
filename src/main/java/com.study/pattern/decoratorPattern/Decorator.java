package com.study.pattern.decoratorPattern;

public abstract class Decorator implements Person {

    protected Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void eat() {
        System.out.println("医生这个角色在吃");
        person.eat();
    }
}
