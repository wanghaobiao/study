package com.study.pattern.statesPattern;

public class TestLuFei {
    public static void main(String[] args) {
        LuFei luFei = new LuFei();
        luFei.setstate(luFei.SECONDGEAR);
        luFei.change();
        luFei.setstate(luFei.THIRDGEAR);
        luFei.change();
        luFei.setstate(luFei.FOURTHGEAR);
        luFei.change();
        luFei.setstate(luFei.ORDINARY);
        luFei.change();
    }
}
