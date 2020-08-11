package com.study.pattern.statesPattern;
public class LuFei {
    public static final LuFeiState Ordinary = new Ordinary();//普通状态
    public static final LuFeiState SecondGear = new SecondGear();//二挡状态
    public static final LuFeiState ThirdGear = new ThirdGear();//三挡状态
    public static final LuFeiState FourthGear = new FourthGear();//四挡状态
    private LuFeiState state = Ordinary;//由于路飞一开始是普通状态，所以我们初始化state为ORDINARY

    public void setstate(LuFeiState state) {
        this.state = state;
    }

    public void change(){
        state.change();
    }
}


