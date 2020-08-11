package com.study.pattern.proxyPattern.cglibProxy;

import io.swagger.annotations.ApiOperation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib子类代理工厂
 */
public class ProxyFactory implements MethodInterceptor {
    // 维护目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * @description  给目标对象创建一个代理对象
     * @date  20/08/11 9:25
     * @author  wanghb
     * @edit
     */
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();
    }

    /**
     * @description  给代理对象添加方法,所有方法都被代理了
     * @param  obj
     * @param  method
     * @param  args
     * @param  proxy
     * @return
     * @date  20/08/11 9:25
     * @author  wanghb
     * @edit
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("向观众问好11");
        //执行目标对象的方法
        Object returnValue = method.invoke(target, args);
        System.out.println("谢谢大家");
        return returnValue;
    }
}
