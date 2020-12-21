package com.study.pattern.mementoPattern.whiteSnapshot;

public class Caretaker {

    private Memento memento;
    /**
     * 备忘录的取值方法
     */
    public Memento getMemento(){
        return this.memento;
    }
    /**
     * 备忘录的赋值方法
     */
    public void setMemento(Memento memento){
        this.memento = memento;
    }
}
