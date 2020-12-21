package com.study.pattern.interpreterPattern;

import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<Variable,Boolean> map = new HashMap<Variable,Boolean>();


    /**
     * @description  用于定义变量属性
     * @param  var
     * @param  value
     * @return  返回结果
     * @date  2020-12-18 11:01
     * @author  wanghb
     * @edit
     */
    public void assign(Variable var , boolean value){
        map.put(var, new Boolean(value));
    }

    /**
     * @description  判断属于什么值
     * @param  var
     * @return  返回结果
     * @date  2020-12-18 11:07
     * @author  wanghb
     * @edit
     */
    public boolean lookup(Variable var) throws IllegalArgumentException{

        Boolean value = map.get(var);
        if(value == null){
            throw new IllegalArgumentException();
        }
        return value.booleanValue();
    }
}
