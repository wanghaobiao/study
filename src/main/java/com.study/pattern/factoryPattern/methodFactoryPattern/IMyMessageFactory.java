package com.study.pattern.factoryPattern.methodFactoryPattern;

public interface IMyMessageFactory {
    public IMyMessage createMessage(String messageType);
}
