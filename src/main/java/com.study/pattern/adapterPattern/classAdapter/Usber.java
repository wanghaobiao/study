package com.study.pattern.adapterPattern.classAdapter;

public class Usber implements Usb {

    @Override
    public void isUsb() {
        System.out.println("USB口");
    }

}
