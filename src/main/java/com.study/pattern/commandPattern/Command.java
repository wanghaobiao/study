package com.study.pattern.commandPattern;

//命令接口
public interface Command {
    //执行操作
    public void excute();
    //撤销操作
    public void unDo();
}
