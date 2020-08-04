package com.study.basics.generic;

import com.server.basis.util.MapUtil;
import javafx.util.Pair;

public class TwoNumGeneric<T,X> {
    private T num1;
    private X num2;

    public T getNum1() {
        return num1;
    }

    public void setNum1(T num1) {
        this.num1 = num1;
    }

    public X getNum2() {
        return num2;
    }

    public void setNum2(X num2) {
        this.num2 = num2;
    }


    public static void main(String[] args) {
        TwoNumGeneric<String,Integer> txTwoNumGeneric = new TwoNumGeneric<>();
    }
}