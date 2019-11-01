package com.study.pattern.mediatorPattern;

//定义具体中介者ConcreteMediator,具体中介者通过协调各同事对象实现协作行为，了解并维护它的各个同事。
public class  ColleagueB extends Colleague {

    public ColleagueB(String name, Mediator mediator) {
        super(name, mediator);
    }
    public void getMessage(String message){
        System.out.println("同事B"+name+"获得信息"+message);
    }
    //同事B与中介者通信
    public void contact(String message){
        mediator.contact(message, this);
    }
}
