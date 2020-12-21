package com.study.pattern.proxyPattern.JDKProxy;
/**
 *  目标对象实现了某一接口
 */
public class Singer implements ISinger{
    @Override
    public void sing(){
        System.out.println("唱一首歌");
    }
    @Override
    public void dancing(){
        System.out.println("跳一支舞");
    }
    @Override
    public void params(String str){
        System.out.println("带参数"+str);
    }
}
