package com.study.basics.generic;

public class NumGeneric <C> {
    private C num;

    public C getNum() {
        return num;
    }

    public void setNum(C num) {
        this.num = num;
    }

    /**
     * 定义在类上  初始化的时候  定义泛型类型
     */
    public static void main(String[] args) {
        NumGeneric<Integer> intNum = new NumGeneric<>();
        intNum.setNum( 10 );
        System.out.println( "Integer:" + intNum.getNum() );
    }
}



