package com.study.MethodFactoryPattern;

public interface IMyMessageFactory {
    public IMyMessage createMessage(String messageType);
}
