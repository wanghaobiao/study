package com.study.SimpleFactoryPattern;

public class SimpleNoodlesFactory {
    public static final int TYPE_LZ = 1;//兰州拉面
    public static final int TYPE_PM = 2;//泡面
    public static final int TYPE_GK = 3;//干扣面

    public static INoodles createNoodles(int type) {
        switch (type) {
            case TYPE_LZ:
                return new LzNoodles();
            case TYPE_PM:
                return new PaoNoodles();
            case TYPE_GK:
            default:
                return new GankouNoodles();
        }
    }

    public static void main(String[] args) {
        INoodles iNoodles = SimpleNoodlesFactory.createNoodles(SimpleNoodlesFactory.TYPE_LZ);
        iNoodles.desc();
    }
}
