package com.study.pattern.statesPattern;

public class LuFei {
    public final static int ORDINARY = 0;//普通状态
    public final static int SECONDGEAR = 1;//二挡状态
    public final static int THIRDGEAR = 2;//三挡状态
    public final static int FOURTHGEAR = 3;//四挡状态
    private int state = ORDINARY;//由于路飞一开始是普通状态，所以我们初始化state为ORDINARY

    public void setstate(int state) {
        this.state = state;
    }

    public void change(){
        if(state == SECONDGEAR){
            System.out.println("路飞开启二挡战斗");
        }else if(state == THIRDGEAR){
            System.out.println("路飞开启三挡战斗");
        }else if(state == FOURTHGEAR){
            System.out.println("路飞开启四挡战斗");
        }else{
            System.out.println("路飞当前为普通状态战斗");
        }
    }


    public static final LuFeiState Ordinary = new Ordinary();//普通状态
    public static final LuFeiState SecondGear = new SecondGear();//二挡状态
    public static final LuFeiState ThirdGear = new ThirdGear();//三挡状态
    public static final LuFeiState FourthGear = new FourthGear();//四挡状态
    private LuFeiState state1 = Ordinary;//由于路飞一开始是普通状态，所以我们初始化state为ORDINARY


    public void change(String string){
        state1.change();
    }
}


