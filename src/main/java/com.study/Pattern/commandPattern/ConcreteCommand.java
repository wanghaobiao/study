package com.study.pattern.commandPattern;

public class ConcreteCommand implements Command {

    // 持有一个接受者Receiver的引用，绑定一个特定的操作
    Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void excute() {
        // 接受者绑定执行动作
        receiver.action();
    }

    @Override
    public void unDo() {
        // 接受者绑定的撤销动作
        receiver.unAction();
    }
}
