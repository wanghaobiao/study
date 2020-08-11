package com.study.pattern.statesPattern;

public class TestLuFei {
    public static void main(String[] args) {
        LuFei luFei = new LuFei();
        luFei.setstate( LuFei.SecondGear );
        luFei.change();
        luFei.setstate( LuFei.ThirdGear );
        luFei.change();
        luFei.setstate( LuFei.FourthGear );
        luFei.change();
        luFei.setstate( LuFei.Ordinary );
        luFei.change();
    }
}
