package com.study.pattern.flyweightPattern.flyweightPatternII;

import java.util.HashMap;
import java.util.Map;

//建筑工厂
public class JianZhuFactory {
    private static final Map<String, TiYuGuan> tygs = new HashMap<String, TiYuGuan>();
    public static TiYuGuan getTyg(String yundong){
        TiYuGuan tyg = tygs.get(yundong);
        if(tyg == null){
            tyg = new TiYuGuan(yundong);
            tygs.put(yundong,tyg);
        }
        return tyg;
    }
    public static int getSize(){
        return tygs.size();
    }
}
