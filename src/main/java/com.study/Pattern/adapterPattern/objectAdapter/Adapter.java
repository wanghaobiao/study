package com.study.pattern.adapterPattern.objectAdapter;


import com.study.pattern.adapterPattern.classAdapter.Ps2;
import com.study.pattern.adapterPattern.classAdapter.Usb;

public class Adapter implements Ps2 {

    private Usb usb;
    public Adapter(Usb usb){
        this.usb = usb;
    }
    @Override
    public void isPs2() {
        usb.isUsb();
    }

}