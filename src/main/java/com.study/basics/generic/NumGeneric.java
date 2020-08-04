package com.study.basics.generic;

public class NumGeneric <C> {
    private C num;

    public C getNum() {
        return num;
    }

    public void setNum(C num) {
        this.num = num;
    }

    public static void main(String[] args) {
        NumGeneric<Integer> intNum = new NumGeneric<>();
        intNum.setNum( 10 );
        System.out.println( "Integer:" + intNum.getNum() );
    }
}



