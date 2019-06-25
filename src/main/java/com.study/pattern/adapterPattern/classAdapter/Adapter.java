package com.study.pattern.adapterPattern.classAdapter;

public class Adapter extends Usber implements Ps2 {
    @Override
    public void isPs2() {
        isUsb();
    }

}