package com.study.pattern.proxyPattern.JDKProxy;

/**
 *  代理对象和目标对象实现相同的接口
 */
public class SingerProxy implements ISinger{
    // 接收目标对象，以便调用sing方法
    private ISinger target;
    public SingerProxy(ISinger target){
        this.target=target;
    }

    // 对目标对象的sing方法进行功能扩展

    @Override
    public void sing() {
        System.out.println("向观众问好1");
        target.sing();
        System.out.println("谢谢大家");
    }

    @Override
    public void dancing() {
        System.out.println("向观众问好2");
        target.dancing();
        System.out.println("谢谢大家");
    }
}
