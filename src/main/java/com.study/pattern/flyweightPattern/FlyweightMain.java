package com.study.pattern.flyweightPattern;
/*
*
* 享元模式
*
* */
public class FlyweightMain {
    public static void main(String[] args) {
        String yundong ="足球";
        for(int i = 1;i <= 5;i++){
            if(i == 2){
                yundong = "篮球";
            }
            TiYuGuan tyg = JianZhuFactory.getTyg(yundong);
            tyg.setName("中国体育馆");
            tyg.setShape("圆形");
            tyg.use();
            System.out.println("对象池中对象数量为："+JianZhuFactory.getSize());
        }
    }
}
